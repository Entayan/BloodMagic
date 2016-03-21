package WayofTime.bloodmagic.registry;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.ConfigHandler;
import WayofTime.bloodmagic.api.Constants;
import WayofTime.bloodmagic.api.orb.BloodOrb;
import WayofTime.bloodmagic.api.registry.OrbRegistry;
import WayofTime.bloodmagic.item.*;
import WayofTime.bloodmagic.item.armour.ItemLivingArmour;
import WayofTime.bloodmagic.item.armour.ItemSentientArmour;
import WayofTime.bloodmagic.item.gear.ItemPackSacrifice;
import WayofTime.bloodmagic.item.gear.ItemPackSelfSacrifice;
import WayofTime.bloodmagic.item.routing.ItemNodeRouter;
import WayofTime.bloodmagic.item.routing.ItemRouterFilter;
import WayofTime.bloodmagic.item.sigil.*;
import WayofTime.bloodmagic.item.soul.*;
import WayofTime.bloodmagic.util.helper.InventoryRenderHelper;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
    public static Item bloodOrb;
    public static BloodOrb orbWeak;
    public static BloodOrb orbApprentice;
    public static BloodOrb orbMagician;
    public static BloodOrb orbMaster;
    public static BloodOrb orbArchmage;
    public static BloodOrb orbTranscendent;

    public static Item bucketEssence;

    public static Item activationCrystal;
    public static Item slate;
    public static Item inscriptionTool;

    public static Item sacrificialDagger;
    public static Item packSelfSacrifice;
    public static Item packSacrifice;
    public static Item daggerOfSacrifice;
    public static Item ritualDiviner;

    public static Item lavaCrystal;

    public static Item boundSword;
    public static Item boundPickaxe;
    public static Item boundAxe;
    public static Item boundShovel;

    public static Item sigilDivination;
    public static Item sigilAir;
    public static Item sigilWater;
    public static Item sigilLava;
    public static Item sigilVoid;
    public static Item sigilGreenGrove;
    public static Item sigilBloodLight;
    public static Item sigilElementalAffinity;
    public static Item sigilHaste;
    public static Item sigilMagnetism;
    public static Item sigilSuppression;
    public static Item sigilFastMiner;
    public static Item sigilSeer;
    public static Item sigilEnderSeverance;
    public static Item sigilWhirlwind;
    public static Item sigilPhantomBridge;
    public static Item sigilCompression;

    public static Item sigilTeleposition;
    public static Item sigilTransposition;

    public static Item itemComponent;
    public static Item itemDemonCrystal;
    public static Item telepositionFocus;

    public static Item bloodShard;

    public static Item livingArmourHelmet;
    public static Item livingArmourChest;
    public static Item livingArmourLegs;
    public static Item livingArmourBoots;

    public static Item sentientArmourHelmet;
    public static Item sentientArmourChest;
    public static Item sentientArmourLegs;
    public static Item sentientArmourBoots;

    public static Item altarMaker;
    public static Item upgradeTome;
    public static Item upgradeTrainer;

    public static Item arcaneAshes;
    public static Item monsterSoul;
    public static Item soulGem;
    public static Item soulSnare;

    public static Item sentientSword;
    public static Item sentientBow;
    public static Item sentientArmourGem;

    public static Item nodeRouter;
    public static Item baseItemFilter;

    public static Item.ToolMaterial boundToolMaterial = EnumHelper.addToolMaterial("BoundToolMaterial", 4, 1, 10, 8, 50);
    public static Item.ToolMaterial soulToolMaterial = EnumHelper.addToolMaterial("SoulToolMaterial", 4, 520, 7, 8, 50);

    public static void init()
    {
        bloodOrb = registerItem(new ItemBloodOrb());
        orbWeak = new BloodOrb("weak", 1, 5000);
        OrbRegistry.registerOrb(orbWeak);
        orbApprentice = new BloodOrb("apprentice", 2, 25000);
        OrbRegistry.registerOrb(orbApprentice);
        orbMagician = new BloodOrb("magician", 3, 150000);
        OrbRegistry.registerOrb(orbMagician);
        orbMaster = new BloodOrb("master", 4, 1000000);
        OrbRegistry.registerOrb(orbMaster);
        orbArchmage = new BloodOrb("archmage", 5, 10000000);
        OrbRegistry.registerOrb(orbArchmage);
        orbTranscendent = new BloodOrb("transcendent", 6, 30000000);
        OrbRegistry.registerOrb(orbTranscendent);

        bucketEssence = registerItem(new ItemBucketEssence());

        activationCrystal = registerItem(new ItemActivationCrystal());
        slate = registerItem(new ItemSlate());
        inscriptionTool = registerItem(new ItemInscriptionTool());

        sacrificialDagger = registerItem(new ItemSacrificialDagger());
        packSacrifice = registerItem(new ItemPackSacrifice());
        packSelfSacrifice = registerItem(new ItemPackSelfSacrifice());
        daggerOfSacrifice = registerItem(new ItemDaggerOfSacrifice());

        ritualDiviner = registerItem(new ItemRitualDiviner());

        lavaCrystal = registerItem(new ItemLavaCrystal());
        GameRegistry.registerFuelHandler(new ItemLavaCrystal());

        boundSword = registerItem(new ItemBoundSword());
        boundPickaxe = registerItem(new ItemBoundPickaxe());
        boundAxe = registerItem(new ItemBoundAxe());
        boundShovel = registerItem(new ItemBoundShovel());

        sigilDivination = registerItem(new ItemSigilDivination());
        sigilAir = registerItem(new ItemSigilAir());
        sigilWater = registerItem(new ItemSigilWater());
        sigilLava = registerItem(new ItemSigilLava());
        sigilVoid = registerItem(new ItemSigilVoid());
        sigilGreenGrove = registerItem(new ItemSigilGreenGrove());
        sigilBloodLight = registerItem(new ItemSigilBloodLight());
        sigilElementalAffinity = registerItem(new ItemSigilElementalAffinity());
        sigilMagnetism = registerItem(new ItemSigilMagnetism());
        sigilSuppression = registerItem(new ItemSigilSuppression());
        sigilHaste = registerItem(new ItemSigilHaste());
        sigilFastMiner = registerItem(new ItemSigilFastMiner());
        sigilSeer = registerItem(new ItemSigilSeer());
        sigilPhantomBridge = registerItem(new ItemSigilPhantomBridge());
        sigilWhirlwind = registerItem(new ItemSigilWhirlwind());
        sigilCompression = registerItem(new ItemSigilCompression());
        sigilEnderSeverance = registerItem(new ItemSigilEnderSeverance());

        sigilTeleposition = registerItem(new ItemSigilTeleposition());
        sigilTransposition = registerItem(new ItemSigilTransposition());

        itemComponent = registerItem(new ItemComponent());
        itemDemonCrystal = registerItem(new ItemDemonCrystal());
        telepositionFocus = registerItem(new ItemTelepositionFocus());

        bloodShard = registerItem(new ItemBloodShard());

        livingArmourHelmet = registerItem(new ItemLivingArmour(EntityEquipmentSlot.HEAD), "ItemLivingArmourHelmet");
        livingArmourChest = registerItem(new ItemLivingArmour(EntityEquipmentSlot.CHEST), "ItemLivingArmourChest");
        livingArmourLegs = registerItem(new ItemLivingArmour(EntityEquipmentSlot.LEGS), "ItemLivingArmourLegs");
        livingArmourBoots = registerItem(new ItemLivingArmour(EntityEquipmentSlot.FEET), "ItemLivingArmourBoots");

        sentientArmourHelmet = registerItem(new ItemSentientArmour(EntityEquipmentSlot.HEAD), "ItemSentientArmourHelmet");
        sentientArmourChest = registerItem(new ItemSentientArmour(EntityEquipmentSlot.CHEST), "ItemSentientArmourChest");
        sentientArmourLegs = registerItem(new ItemSentientArmour(EntityEquipmentSlot.LEGS), "ItemSentientArmourLegs");
        sentientArmourBoots = registerItem(new ItemSentientArmour(EntityEquipmentSlot.FEET), "ItemSentientArmourBoots");

        altarMaker = registerItem(new ItemAltarMaker());
        upgradeTome = registerItem(new ItemUpgradeTome());
        upgradeTrainer = registerItem(new ItemUpgradeTrainer());

        arcaneAshes = registerItem(new ItemArcaneAshes());
        monsterSoul = registerItem(new ItemMonsterSoul());
        soulGem = registerItem(new ItemSoulGem());
        soulSnare = registerItem(new ItemSoulSnare());

        sentientSword = registerItem(new ItemSentientSword());
        sentientBow = registerItem(new ItemSentientBow());
        sentientArmourGem = registerItem(new ItemSentientArmourGem());

        nodeRouter = registerItem(new ItemNodeRouter());
        baseItemFilter = registerItem(new ItemRouterFilter());
    }

    public static void initRenders()
    {
        InventoryRenderHelper renderHelper = BloodMagic.proxy.getRenderHelper();

        renderHelper.itemRenderAll(bloodOrb);
        OrbRegistry.registerOrbTexture(orbWeak, new ResourceLocation(Constants.Mod.DOMAIN + "ItemBloodOrbWeak"));
        OrbRegistry.registerOrbTexture(orbApprentice, new ResourceLocation(Constants.Mod.DOMAIN + "ItemBloodOrbApprentice"));
        OrbRegistry.registerOrbTexture(orbMagician, new ResourceLocation(Constants.Mod.DOMAIN + "ItemBloodOrbMagician"));
        OrbRegistry.registerOrbTexture(orbMaster, new ResourceLocation(Constants.Mod.DOMAIN + "ItemBloodOrbMaster"));
        OrbRegistry.registerOrbTexture(orbArchmage, new ResourceLocation(Constants.Mod.DOMAIN + "ItemBloodOrbArchmage"));
        OrbRegistry.registerOrbTexture(orbTranscendent, new ResourceLocation(Constants.Mod.DOMAIN + "ItemBloodOrbTranscendent"));

        renderHelper.itemRender(bucketEssence);

        renderHelper.itemRender(sentientBow, 0, "ItemSentientBow");
        renderHelper.itemRender(sentientBow, 1, "ItemSentientBow_pulling_0");
        renderHelper.itemRender(sentientBow, 2, "ItemSentientBow_pulling_1");
        renderHelper.itemRender(sentientBow, 3, "ItemSentientBow_pulling_2");

        renderHelper.itemRender(sentientArmourGem, 0, "ItemSentientArmourGem0");
        renderHelper.itemRender(sentientArmourGem, 1, "ItemSentientArmourGem1");
    }

    private static Item registerItem(Item item, String name)
    {
        if (!ConfigHandler.itemBlacklist.contains(name))
        {
            GameRegistry.registerItem(item, name);
            BloodMagic.proxy.tryHandleItemModel(item, name);
        }

        return item;
    }

    private static Item registerItem(Item item)
    {
        if (item.getRegistryName() == null)
        {
            BloodMagic.instance.getLogger().error("Attempted to register Item {} without setting a registry name. Item will not be registered. Please report this.", item.getClass().getCanonicalName());
            return item;
        }

        String itemName = item.getRegistryName().split(":")[1];
        if (!ConfigHandler.itemBlacklist.contains(itemName))
        {
            GameRegistry.registerItem(item);
            BloodMagic.proxy.tryHandleItemModel(item, itemName);
        }

        return item;
    }
}
