package cipo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

public class CipoGlobalFile {

	public static void main(String[] args) throws IOException 
	{
		
		Instant start = Instant.now();
		System.out.println("Start : " + start);
		
		String searchFile = "916100-00.xml";
		String localfolder = "C:\\raj\\";
		String cipourl = "https://opic-cipo.ca/cipo/client_downloads/Trademarks_Historical_2019_10/";
		
		SortedMap<String, Integer> cipohistfiles = new TreeMap<String, Integer>();
	
		loadMap(cipohistfiles);
				
		int idx = searchFile.indexOf('-');
		int tmid = Integer.parseInt(searchFile.substring(0, idx));
		String cipozipfilename = "";
		
		for (String i : cipohistfiles.keySet()) 
		{
		    
			if (cipohistfiles.get(i).intValue() >=tmid)
			{
				System.out.println("key: " + i);
				cipozipfilename = i;
				break;
			}
		}
		
		
		String filename = cipourl+cipozipfilename;
    	URL url = new URL(filename);
    	FileUtils.copyURLToFile(url, new File(localfolder+cipozipfilename));
    	
	    ZipFile zipFile = null;
	    //searchFile = "2039281-00.xml";
	 
	    try {

	    	zipFile = new ZipFile(localfolder+cipozipfilename);
	    	//zipFile = new ZipFile("C:\\raj\\CA-TMK-UPDATE_2020-07-07-2020-07-13_7719_2036764_001.zip");
	    	System.out.println(" zipfile" +zipFile.toString());
	    	ZipEntry e = zipFile.getEntry(searchFile);
	    	System.out.println(" zipentry getname" +e.getName());
	    	InputStream is = zipFile.getInputStream(e);
	    	FileUtils.copyInputStreamToFile(is, new File(localfolder+e.getName()));
	    	is.close();
	    	
	    	zipFile.close();
	    
	    	File deletefile = new File(localfolder+cipozipfilename); 
	          
	        if(deletefile.delete()) 
	        { 
	            System.out.println(localfolder+cipozipfilename+" File deleted successfully"); 
	        } 
	    	
	    }
	    catch (IOException ioe) {
	    	System.out.println("Error opening zip file" + ioe);
	    }
	    finally {
	    	try {
	    		if (zipFile!=null) {
	    			zipFile.close();
	    		}
	    	}
	    	catch (IOException ioe) {
	    		System.out.println("Error while closing zip file" + ioe);
	    	}
	    }
	    
	    Instant finish = Instant.now();
	    System.out.println("End : " + finish);
	    long dur = Duration.between(start, finish).getSeconds();
	    System.out.println("Took "+dur+" seconds to run");  

	}

	static void loadMap(SortedMap<String, Integer> cipohistfiles)
	{
		//filename = "https://opic-cipo.ca/cipo/client_downloads/Trademarks_Historical_2019_10/CA-TMK-GLOBAL_2019-10-05_index.txt";
    	
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_111_168014_001.zip", new Integer(168014));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_168015_226048_002.zip", new Integer(226048));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_226049_286699_003.zip", new Integer(286699));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_286700_334780_004.zip", new Integer(334780));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_334781_386408_005.zip", new Integer(386408));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_386409_428483_006.zip", new Integer(428483));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_428484_461127_007.zip", new Integer(461127));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_461128_492128_008.zip", new Integer(492128));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_492129_523818_009.zip", new Integer(523818));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_523819_555006_010.zip", new Integer(555006));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_555007_589302_011.zip", new Integer(589302));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_589303_631073_012.zip", new Integer(631073));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_631074_678843_013.zip", new Integer(678843));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_678844_723847_014.zip", new Integer(723847));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_723848_764535_015.zip", new Integer(764535));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_764536_793766_016.zip", new Integer(793766));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_793767_817874_017.zip", new Integer(817874));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_817875_840135_018.zip", new Integer(840135));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_840136_862105_019.zip", new Integer(862105));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_862106_883502_020.zip", new Integer(883502));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_883503_903131_021.zip", new Integer(903131));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_903132_916992_022.zip", new Integer(916992));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_916993_923623_023.zip", new Integer(923623));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_923624_970573_024.zip", new Integer(970573));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_970574_972846_025.zip", new Integer(972846));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_972847_1000140_026.zip", new Integer(1000140));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1000141_1019415_027.zip", new Integer(1019415));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1019416_1038133_028.zip", new Integer(1038133));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1038134_1057612_029.zip", new Integer(1057612));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1057613_1077127_030.zip", new Integer(1077127));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1077128_1096168_031.zip", new Integer(1096168));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1096169_1112172_032.zip", new Integer(1112172));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1112173_1131663_033.zip", new Integer(1131663));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1131664_1147910_034.zip", new Integer(1147910));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1147911_1166839_035.zip", new Integer(1166839));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1166840_1184340_036.zip", new Integer(1184340));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1184341_1200002_037.zip", new Integer(1200002));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1200003_1219651_038.zip", new Integer(1219651));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1219652_1239715_039.zip", new Integer(1239715));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1239716_1257423_040.zip", new Integer(1257423));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1257424_1271136_041.zip", new Integer(1271136));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1271137_1284322_042.zip", new Integer(1284322));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1284323_1297318_043.zip", new Integer(1297318));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1297319_1309627_044.zip", new Integer(1309627));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1309628_1322552_045.zip", new Integer(1322552));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1322553_1335346_046.zip", new Integer(1335346));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1335347_1348472_047.zip", new Integer(1348472));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1348473_1361053_048.zip", new Integer(1361053));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1361054_1374003_049.zip", new Integer(1374003));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1374004_1387462_050.zip", new Integer(1387462));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1387463_1399918_051.zip", new Integer(1399918));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1399919_1412643_052.zip", new Integer(1412643));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1412644_1425893_053.zip", new Integer(1425893));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1425894_1439021_054.zip", new Integer(1439021));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1439022_1451813_055.zip", new Integer(1451813));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1451814_1463852_056.zip", new Integer(1463852));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1463853_1476470_057.zip", new Integer(1476470));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1476471_1488847_058.zip", new Integer(1488847));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1488848_1500904_059.zip", new Integer(1500904));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1500905_1513805_060.zip", new Integer(1513805));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1513806_1525974_061.zip", new Integer(1525974));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1525975_1542306_062.zip", new Integer(1542306));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1542307_1555226_063.zip", new Integer(1555226));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1555227_1568107_064.zip", new Integer(1568107));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1568108_1579641_065.zip", new Integer(1579641));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1579642_1591595_066.zip", new Integer(1591595));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1591596_1603968_067.zip", new Integer(1603968));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1603969_1616616_068.zip", new Integer(1616616));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1616617_1628362_069.zip", new Integer(1628362));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1628363_1640313_070.zip", new Integer(1640313));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1640314_1652518_071.zip", new Integer(1652518));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1652519_1664139_072.zip", new Integer(1664139));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1664140_1676954_073.zip", new Integer(1676954));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1676955_1689047_074.zip", new Integer(1689047));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1689048_1699899_075.zip", new Integer(1699899));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1699900_1710124_076.zip", new Integer(1710124));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1710125_1720007_077.zip", new Integer(1720007));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1720008_1730128_078.zip", new Integer(1730128));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1730129_1740089_079.zip", new Integer(1740089));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1740090_1750100_080.zip", new Integer(1750100));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1750101_1759234_081.zip", new Integer(1759234));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1759235_1769110_082.zip", new Integer(1769110));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1769111_1778899_083.zip", new Integer(1778899));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1778900_1789516_084.zip", new Integer(1789516));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1789517_1800018_085.zip", new Integer(1800018));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1800019_1809467_086.zip", new Integer(1809467));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1809468_1818930_087.zip", new Integer(1818930));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1818931_1828842_088.zip", new Integer(1828842));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1828843_1838538_089.zip", new Integer(1838538));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1838539_1847754_090.zip", new Integer(1847754));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1847755_1857483_091.zip", new Integer(1857483));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1857484_1867228_092.zip", new Integer(1867228));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1867229_1876841_093.zip", new Integer(1876841));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1876842_1886722_094.zip", new Integer(1886722));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1886723_1896561_095.zip", new Integer(1896561));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1896562_1906363_096.zip", new Integer(1906363));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1906364_1915949_097.zip", new Integer(1915949));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1915950_1926419_098.zip", new Integer(1926419));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1926420_1936982_099.zip", new Integer(1936982));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1936983_1947007_100.zip", new Integer(1947007));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1947008_1957092_101.zip", new Integer(1957092));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1957093_1966412_102.zip", new Integer(1966412));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1966413_1974060_103.zip", new Integer(1974060));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1974061_1979997_104.zip", new Integer(1979997));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1979998_1985482_105.zip", new Integer(1985482));
		cipohistfiles.put("CA-TMK-GLOBAL_2019-10-05_1985483_1988747_106.zip", new Integer(1988747));


		
		
	}
}


if (true)
{
	checkforSixpointcondition();
}
else if (false)
	type name = new type();
else 
	type name = new type();
		
		