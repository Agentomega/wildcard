package omega.wildcard;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class Wildcard {
	private static final String NAME = Constants.NAME;
	private static final String VERSION = Constants.VERSION;
	
	private static Logger logger;
	
	private static boolean appliedEnergisticsLoaded;
	private static boolean botaniaLoaded;
	private static boolean enderIOLoaded;
	private static boolean refinedStorageLoaded;
	private static boolean thermalDynamicsLoaded;
	
	@EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        
        botaniaLoaded = Loader.isModLoaded("botania");
    }
	
	@EventHandler
    public static void init(FMLInitializationEvent event)
    {
        logger.info("Initializing " + NAME + " version " + VERSION);
    }
	
	/*
	 * Constructor to appease Sonar
	 */
	
	private Wildcard() {
		//noop
	}
}
