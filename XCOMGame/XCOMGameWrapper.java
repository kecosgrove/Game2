package XCOMGame;

import javalib.funworld.World;

public class XCOMGameWrapper {

    private static String[] initNACities() {
        String[] array = new String[20];
        array[0] = "Vancouver, Canada";
        array[1] = "Edmonton, Canada";
        array[2] = "Toronto, Canada";
        array[3] = "Montreal, Canada";
        array[4] = "San Francisco, United States";
        array[5] = "Santa Fe, United States";
        array[6] = "Seattle, United States";
        array[7] = "Houston, United States";
        array[8] = "Salt Lake City, United States";
        array[9] = "Minneapolis, United States";
        array[10] = "Chicago, United States";
        array[11] = "St. Louis, United States";
        array[12] = "Tampa Bay, United States";
        array[13] = "Washington D.C., United States";
        array[14] = "New York, United States";
        array[15] = "Boston, United States";
        array[16] = "Atlanta, United States";
        array[17] = "Monterrey, Mexico";
        array[18] = "Mexico City, Mexico";
        array[19] = "Havana, Cuba";
        return array;
    }

    private static String[] initSACities() {
        String[] array = new String[20];
        array[0] = "Panama, Panama";
        array[1] = "Caracas, Venezuela";
        array[2] = "Medellin, Colombia";
        array[3] = "Bogota, Colombia";
        array[4] = "Lima, Peru";
        array[5] = "Ica, Peru";
        array[6] = "Santiago, Chile";
        array[7] = "Concepcion, Chile";
        array[8] = "Mendoza, Argentina";
        array[9] = "Cordoba, Argentina";
        array[10] = "Buenos Aires, Argentina";
        array[11] = "Montevideo, Uruguay";
        array[12] = "Sao Paulo, Brazil";
        array[13] = "Rio de Janeiro, Brazil";
        array[14] = "Quito, Ecuador";
        array[15] = "Guayaquil, Ecuador";
        array[16] = "Cochabamba, Bolivia";
        array[17] = "Asuncion, Paraguay";
        array[18] = "Salvador, Brazil";
        array[19] = "Georgetown, Guyana";
        return array;
    }

    private static String[] initEUCities() {
        String[] array = new String[20];
        array[0] = "Dublin, Ireland";
        array[1] = "Liverpool, United Kingdom";
        array[2] = "London, United Kingdom";
        array[3] = "Helsinki, Finland";
        array[4] = "Lisbon, Portugal";
        array[5] = "Madrid, Spain";
        array[6] = "Valencia, Spain";
        array[7] = "Marseille, France";
        array[8] = "Paris, France";
        array[9] = "Budapest, Hungary";
        array[10] = "Rome, Italy";
        array[11] = "Prague, Czech Republic";
        array[12] = "Berlin, Germany";
        array[13] = "Hamburg, Germany";
        array[14] = "Warsaw, Poland";
        array[15] = "Zurich, Switzerland";
        array[16] = "Athens, Greece";
        array[17] = "Kiev, Ukraine";
        array[18] = "Stockholm, Sweden";
        array[19] = "Oslo, Norway";
        return array;
    }

    private static String[] initASCities() {
        String[] array = new String[20];
        array[0] = "Moscow, Russia";
        array[1] = "Kazan, Russia";
        array[2] = "Astana, Kazakhstan";
        array[3] = "Kabul, Afghanistan";
        array[4] = "Islamabad, Pakistan";
        array[5] = "New Delhi, India";
        array[6] = "Jaipur, India";
        array[7] = "Ho Chi Mihn City, Vietnam";
        array[8] = "Ulaanbaatar, Mongolia";
        array[9] = "Xian, China";
        array[10] = "Beijing, China";
        array[11] = "Seoul, South Korea";
        array[12] = "Busan, South Korea";
        array[13] = "Pyongyang, North Korea";
        array[14] = "Tokyo, Japan";
        array[15] = "Osaka, Japan";
        array[16] = "Hong Kong, China";
        array[17] = "Taipei, Taiwan";
        array[18] = "Hanoi, Vietnam";
        array[19] = "Bangkok, Thailand";
        return array;
    }

    private static String[] initAFCities() {
        String[] array = new String[20];
        array[0] = "Rabat, Morocco";
        array[1] = "Algiers, Algeria";
        array[2] = "Tunis, Tunisia";
        array[3] = "Tripoli, Libya";
        array[4] = "Cairo, Egypt";
        array[5] = "Accra, Ghana";
        array[6] = "Dakar, Senegal";
        array[7] = "Lagos, Nigeria";
        array[8] = "Abuja, Nigeria";
        array[9] = "Cape Town, South Africa";
        array[10] = "Pretoria, South Africa";
        array[11] = "Nairobi, Kenya";
        array[12] = "N'Djamena, Chad";
        array[13] = "Khartoum, Sudan";
        array[14] = "Mogadishu, Somalia";
        array[15] = "Dar es Salaam, Tanzania";
        array[16] = "Maputo, Mozambique";
        array[17] = "Zanzibar, Tanzania";
        array[18] = "Kampala, Uganda";
        array[19] = "Juba, South Sudan";
        return array;
    }

    private static String[] initOCCities() {
        String[] array = new String[20];
        array[0] = "Auckland, New Zealand";
        array[1] = "Wellington, New Zealand";
        array[2] = "Christchurch, New Zealand";
        array[3] = "Melbourne, Australia";
        array[4] = "Sydney, Australia";
        array[5] = "Brisbane, Australia";
        array[6] = "Perth, Australia";
        array[7] = "Port Moresby, Papua New Guinea";
        array[8] = "Kuala Lumpur, Malaysia";
        array[9] = "Medan, Indonesia";
        array[10] = "Singapore, Singapore";
        array[11] = "Jakarta, Indonesia";
        array[12] = "Surabaya, Indonesia";
        array[13] = "Malang, Indonesia";
        array[14] = "Davao, Philippines";
        array[15] = "Manila, Philippines";
        array[16] = "Apia, Samoa";
        array[17] = "Dungun, Malaysia";
        array[18] = "Batu Pahat, Malaysia";
        array[19] = "Sibu, Malaysia";
        return array;
    }

    public static Continent[] initContinents() {
        String[] NACities = initNACities();
        String[] SACities = initSACities();
        String[] EUCities = initEUCities();
        String[] ASCities = initASCities();
        String[] AFCities = initAFCities();
        String[] OCCities = initOCCities();
        Continent[] array = new Continent[6];
        array[0] = new Continent(0, "North America", NACities);
        array[1] = new Continent(0, "South America", SACities);
        array[2] = new Continent(0, "Europe", EUCities);
        array[3] = new Continent(0, "Asia", ASCities);
        array[4] = new Continent(0, "Africa", AFCities);
        array[5] = new Continent(0, "Oceania", OCCities);
        return array;
    }

    public static void main(String[] args) {
        MissionSlot[] missions = new MissionSlot[4];
        for (int i = 0; i < missions.length; i++) {
            missions[i] = new MTMSlot(-1);
        }
        MapWorld world = new MapWorld(new DateContainer(2015, 60, 0, 0), missions, initContinents());
        world.bigBang(MapWorld.screenHeight, MapWorld.screenWidth, .1);
    }

}
