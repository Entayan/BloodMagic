package WayofTime.bloodmagic.item.armour;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.api.Constants;
import WayofTime.bloodmagic.api.livingArmour.LivingArmourUpgrade;
import WayofTime.bloodmagic.api.util.helper.NBTHelper;
import WayofTime.bloodmagic.client.IMeshProvider;
import WayofTime.bloodmagic.livingArmour.LivingArmour;
import WayofTime.bloodmagic.registry.ModItems;
import WayofTime.bloodmagic.util.helper.TextHelper;

import com.google.common.collect.Multimap;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ItemLivingArmour extends ItemArmor implements ISpecialArmor, IMeshProvider
{
    public static String[] names = { "helmet", "chest", "legs", "boots" };

    //TODO: Save/delete cache periodically.
    public static Map<ItemStack, LivingArmour> armourMap = new HashMap<ItemStack, LivingArmour>();

    public ItemLivingArmour(EntityEquipmentSlot armorType)
    {
        super(ItemArmor.ArmorMaterial.IRON, 0, armorType);
        setUnlocalizedName(Constants.Mod.MODID + ".livingArmour.");
        setMaxDamage(250);
        setCreativeTab(BloodMagic.tabBloodMagic);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (this == ModItems.livingArmourChest || this == ModItems.livingArmourHelmet || this == ModItems.livingArmourBoots)
        {
            return "bloodmagic:models/armor/livingArmour_layer_1.png";
        }

        if (this == ModItems.livingArmourLegs)
        {
            return "bloodmagic:models/armor/livingArmour_layer_2.png";
        } else
        {
            return null;
        }
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage, int slot)
    {
        double armourReduction = 0.0;
        double damageAmount = 0.25;

        if (this == ModItems.livingArmourBoots || this == ModItems.livingArmourHelmet)
        {
            damageAmount = 3d / 20d * 0.6;
        } else if (this == ModItems.livingArmourLegs)
        {
            damageAmount = 6d / 20d * 0.6;
        } else if (this == ModItems.livingArmourChest)
        {
            damageAmount = 0.64;
        }

        double armourPenetrationReduction = 0;

        int maxAbsorption = 100000;

        if (source.equals(DamageSource.drown))
        {
            return new ArmorProperties(-1, 0, 0);
        }

        if (source.equals(DamageSource.outOfWorld))
        {
            return new ArmorProperties(-1, 0, 0);
        }

        if (this == ModItems.livingArmourChest)
        {
            armourReduction = 0.24 / 0.64; // This values puts it at iron level

            ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (helmet == null || leggings == null || boots == null)
            {
                damageAmount *= (armourReduction);

                return new ArmorProperties(-1, damageAmount, maxAbsorption);
            }

            if (helmet.getItem() instanceof ItemLivingArmour && leggings.getItem() instanceof ItemLivingArmour && boots.getItem() instanceof ItemLivingArmour)
            {
                double remainder = 1; // Multiply this number by the armour upgrades for protection

                if (armourMap.containsKey(stack))
                {
                    LivingArmour armour = armourMap.get(stack);
                    if (armour != null && isEnabled(stack))
                    {
                        for (Entry<String, LivingArmourUpgrade> entry : armour.upgradeMap.entrySet())
                        {
                            LivingArmourUpgrade upgrade = entry.getValue();
                            remainder *= (1 - upgrade.getArmourProtection(player, source));
                            /*
                             * Just as a side note, if one upgrade provides
                             * upgrade.getArmourProtection(source) = 0.5, the
                             * armour would have a diamond level protection
                             */
                        }
                    }
                }

                armourReduction = armourReduction + (1 - remainder) * (1 - armourReduction);
                damageAmount *= (armourReduction);

                if (source.isUnblockable())
                {
                    return new ArmorProperties(-1, damageAmount * armourPenetrationReduction, maxAbsorption);
                }

                return new ArmorProperties(-1, damageAmount, maxAbsorption);
            }
        } else
        {
            if (source.isUnblockable())
            {
                return new ArmorProperties(-1, damageAmount * armourPenetrationReduction, maxAbsorption);
            }

            return new ArmorProperties(-1, damageAmount, maxAbsorption);
        }

        return new ArmorProperties(-1, 0, 0);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        if (armor.getItem() == ModItems.livingArmourHelmet)
        {
            return 3;
        }

        if (armor.getItem() == ModItems.livingArmourChest)
        {
            return 8;
        }

        if (armor.getItem() == ModItems.livingArmourLegs)
        {
            return 6;
        }

        if (armor.getItem() == ModItems.livingArmourBoots)
        {
            return 3;
        }

        return 5;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        return; // Armour shouldn't get damaged... for now
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
    {
        stack = NBTHelper.checkNBT(stack);

        if (this == ModItems.livingArmourChest)
        {
            LivingArmour armour = getLivingArmour(stack);
            for (Entry<String, LivingArmourUpgrade> entry : armour.upgradeMap.entrySet())
            {
                LivingArmourUpgrade upgrade = entry.getValue();
                if (upgrade != null)
                {
                    tooltip.add(TextHelper.localize("tooltip.BloodMagic.livingArmour.upgrade.level", TextHelper.localize(upgrade.getUnlocalizedName()), (upgrade.getUpgradeLevel() + 1)));
                }
            }

            tooltip.add(TextHelper.localizeEffect("tooltip.BloodMagic.livingArmour.upgrade.points", armour.totalUpgradePoints, armour.maxUpgradePoints));
        }

        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {
        super.onArmorTick(world, player, stack);

        if (world.isRemote)
        {
            return;
        }

        if (this == ModItems.livingArmourChest)
        {
            if (!armourMap.containsKey(stack))
            {
                armourMap.put(stack, getLivingArmour(stack));
            }

            LivingArmour armour = armourMap.get(stack);
            if (LivingArmour.hasFullSet(player))
            {
                this.setIsEnabled(stack, true);
                armour.onTick(world, player);
            } else
            {
                this.setIsEnabled(stack, false);
            }

            setLivingArmour(stack, armour, false);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        if (this == ModItems.livingArmourChest && isEnabled(stack))
        {
            LivingArmour armour = ItemLivingArmour.getLivingArmour(stack);

            return armour.getAttributeModifiers();
        }

        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + names[armorType.getIndex()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getMeshDefinition()
    {
        return new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                assert getCustomLocation() != null;
                if (stack.getItem() == ModItems.livingArmourHelmet)
                    return new ModelResourceLocation(getCustomLocation(), "armour=head");
                else if (stack.getItem() == ModItems.livingArmourChest)
                    return new ModelResourceLocation(getCustomLocation(), "armour=body");
                else if (stack.getItem() == ModItems.livingArmourLegs)
                    return new ModelResourceLocation(getCustomLocation(), "armour=leg");
                else
                    return new ModelResourceLocation(getCustomLocation(), "armour=feet");
            }
        };
    }

    @Override
    public ResourceLocation getCustomLocation()
    {
        return new ResourceLocation(Constants.Mod.MODID, "item/ItemLivingArmour");
    }

    @Override
    public List<String> getVariants()
    {
        List<String> ret = new ArrayList<String>();
        ret.add("armour=head");
        ret.add("armour=body");
        ret.add("armour=leg");
        ret.add("armour=feet");
        return ret;
    }

    public static LivingArmour getLivingArmour(ItemStack stack)
    {
        NBTTagCompound livingTag = getArmourTag(stack);

        LivingArmour livingArmour = new LivingArmour();
        livingArmour.readFromNBT(livingTag);

        return livingArmour;
    }

    public void setLivingArmour(ItemStack stack, LivingArmour armour, boolean forceWrite)
    {
        NBTTagCompound livingTag = new NBTTagCompound();

        if (!forceWrite)
        {
            livingTag = getArmourTag(stack);
            armour.writeDirtyToNBT(livingTag);
        } else
        {
            armour.writeToNBT(livingTag);
        }

        setArmourTag(stack, livingTag);
    }

    public static NBTTagCompound getArmourTag(ItemStack stack)
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = stack.getTagCompound();
        return tag.getCompoundTag(Constants.NBT.LIVING_ARMOUR);
    }

    public void setArmourTag(ItemStack stack, NBTTagCompound livingTag)
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = stack.getTagCompound();

        tag.setTag(Constants.NBT.LIVING_ARMOUR, livingTag);
    }

    public static LivingArmourUpgrade getUpgrade(String uniqueIdentifier, ItemStack stack)
    {
        if (!armourMap.containsKey(stack))
        {
            armourMap.put(stack, getLivingArmour(stack));
        }

        LivingArmour armour = armourMap.get(stack);

        for (Entry<String, LivingArmourUpgrade> entry : armour.upgradeMap.entrySet())
        {
            if (entry.getKey().equals(uniqueIdentifier))
            {
                return entry.getValue();
            }
        }

        return null;
    }

    public void setIsEnabled(ItemStack stack, boolean bool)
    {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setBoolean("enabled", bool);
    }

    public boolean isEnabled(ItemStack stack)
    {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getBoolean("enabled");
    }
}
