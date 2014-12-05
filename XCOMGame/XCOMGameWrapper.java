package XCOMGame;

import javalib.funworld.World;
import javalib.worldimages.Posn;

public class XCOMGameWrapper {

    private static City[] initNACities() {
        City[] array = new City[20];
        array[0] = new City(new Posn(195, 210), "Vancouver, Canada");
        array[1] = new City(new Posn(250, 180), "Edmonton, Canada");
        array[2] = new City(new Posn(350, 220), "Toronto, Canada");
        array[3] = new City(new Posn(318, 330), "Merida, Mexico");
        array[4] = new City(new Posn(200, 245), "San Francisco, United States");
        array[5] = new City(new Posn(250, 260), "Santa Fe, United States");
        array[6] = new City(new Posn(208, 265), "Los Angeles, United States");
        array[7] = new City(new Posn(292, 287), "Houston, United States");
        array[8] = new City(new Posn(245, 240), "Salt Lake City, United States");
        array[9] = new City(new Posn(295, 225), "Minneapolis, United States");
        array[10] = new City(new Posn(320, 235), "Chicago, United States");
        array[11] = new City(new Posn(288, 250), "Kansas City, United States");
        array[12] = new City(new Posn(404, 335), "San Juan, Puerto Rico");
        array[13] = new City(new Posn(360, 250), "Washington D.C., United States");
        array[14] = new City(new Posn(350, 300), "Miami, United States");
        array[15] = new City(new Posn(385, 240), "Boston, United States");
        array[16] = new City(new Posn(335, 270), "Atlanta, United States");
        array[17] = new City(new Posn(280, 303), "Monterrey, Mexico");
        array[18] = new City(new Posn(279, 330), "Mexico City, Mexico");
        array[19] = new City(new Posn(345, 320), "Havana, Cuba");
        return array;
    }

    private static City[] initSACities() {
        City[] array = new City[20];
        array[0] = new City(new Posn(355, 377), "Panama, Panama");
        array[1] = new City(new Posn(399, 375), "Caracas, Venezuela");
        array[2] = new City(new Posn(372, 370), "Baranquilla, Colombia");
        array[3] = new City(new Posn(375, 395), "Bogota, Colombia");
        array[4] = new City(new Posn(361, 462), "Lima, Peru");
        array[5] = new City(new Posn(382, 490), "Tacna, Peru");
        array[6] = new City(new Posn(383, 550), "Santiago, Chile");
        array[7] = new City(new Posn(375, 595), "Puerto Montt, Chile");
        array[8] = new City(new Posn(450, 550), "Porto Alegre, Brazil");
        array[9] = new City(new Posn(405, 550), "Cordoba, Argentina");
        array[10] = new City(new Posn(428, 560), "Buenos Aires, Argentina");
        array[11] = new City(new Posn(435, 561), "Montevideo, Uruguay");
        array[12] = new City(new Posn(463, 523), "Curitiba, Brazil");
        array[13] = new City(new Posn(482, 510), "Rio de Janeiro, Brazil");
        array[14] = new City(new Posn(360, 415), "Quito, Ecuador");
        array[15] = new City(new Posn(350, 430), "Guayaquil, Ecuador");
        array[16] = new City(new Posn(415, 495), "Santa Cruz, Bolivia");
        array[17] = new City(new Posn(432, 517), "Asuncion, Paraguay");
        array[18] = new City(new Posn(500, 465), "Salvador, Brazil");
        array[19] = new City(new Posn(443, 397), "Paramaribo, Suriname");
        return array;
    }

    private static City[] initEUCities() {
        City[] array = new City[20];
        array[0] = new City(new Posn(617, 188), "Dublin, Ireland");
        array[1] = new City(new Posn(680, 177), "Copenhagen, Denmark");
        array[2] = new City(new Posn(640, 193), "London, United Kingdom");
        array[3] = new City(new Posn(720, 156), "Helsinki, Finland");
        array[4] = new City(new Posn(610, 250), "Lisbon, Portugal");
        array[5] = new City(new Posn(623, 240), "Madrid, Spain");
        array[6] = new City(new Posn(732, 223), "Bucharest, Romania");
        array[7] = new City(new Posn(656, 230), "Marseille, France");
        array[8] = new City(new Posn(646, 204), "Paris, France");
        array[9] = new City(new Posn(705, 211), "Budapest, Hungary");
        array[10] = new City(new Posn(684, 236), "Rome, Italy");
        array[11] = new City(new Posn(687, 199), "Prague, Czech Republic");
        array[12] = new City(new Posn(685, 190), "Berlin, Germany");
        array[13] = new City(new Posn(682, 257), "Palmero, Italy");
        array[14] = new City(new Posn(714, 194), "Warsaw, Poland");
        array[15] = new City(new Posn(669, 212), "Zurich, Switzerland");
        array[16] = new City(new Posn(721, 251), "Athens, Greece");
        array[17] = new City(new Posn(745, 200), "Kiev, Ukraine");
        array[18] = new City(new Posn(703, 158), "Stockholm, Sweden");
        array[19] = new City(new Posn(674, 158), "Oslo, Norway");
        return array;
    }

    private static City[] initASCities() {
        City[] array = new City[20];
        array[0] = new City(new Posn(771, 177), "Moscow, Russia");
        array[1] = new City(new Posn(814, 176), "Kazan, Russia");
        array[2] = new City(new Posn(893, 198), "Astana, Kazakhstan");
        array[3] = new City(new Posn(887, 268), "Kabul, Afghanistan");
        array[4] = new City(new Posn(925, 385), "Colombo, Sri Lanka");
        array[5] = new City(new Posn(914, 293), "New Delhi, India");
        array[6] = new City(new Posn(899, 333), "Mumbai, India");
        array[7] = new City(new Posn(1019, 367), "Ho Chi Mihn City, Vietnam");
        array[8] = new City(new Posn(1021, 205), "Ulaanbaatar, Mongolia");
        array[9] = new City(new Posn(1026, 268), "Xian, China");
        array[10] = new City(new Posn(1052, 242), "Beijing, China");
        array[11] = new City(new Posn(1090, 254), "Seoul, South Korea");
        array[12] = new City(new Posn(1097, 265), "Busan, South Korea");
        array[13] = new City(new Posn(1089, 245), "Pyongyang, North Korea");
        array[14] = new City(new Posn(1138, 260), "Tokyo, Japan");
        array[15] = new City(new Posn(1144, 230), "Sapporo, Japan");
        array[16] = new City(new Posn(1070, 283), "Shanghai, China");
        array[17] = new City(new Posn(1072, 308), "Taipei, Taiwan");
        array[18] = new City(new Posn(1014, 329), "Hanoi, Vietnam");
        array[19] = new City(new Posn(996, 353), "Bangkok, Thailand");
        return array;
    }

    private static City[] initAFCities() {
        City[] array = new City[20];
        array[0] = new City(new Posn(613, 270), "Rabat, Morocco");
        array[1] = new City(new Posn(647, 258), "Algiers, Algeria");
        array[2] = new City(new Posn(674, 256), "Tunis, Tunisia");
        array[3] = new City(new Posn(686, 272), "Tripoli, Libya");
        array[4] = new City(new Posn(749, 284), "Cairo, Egypt");
        array[5] = new City(new Posn(635, 388), "Accra, Ghana");
        array[6] = new City(new Posn(577, 356), "Dakar, Senegal");
        array[7] = new City(new Posn(650, 385), "Lagos, Nigeria");
        array[8] = new City(new Posn(663, 377), "Abuja, Nigeria");
        array[9] = new City(new Posn(705, 562), "Cape Town, South Africa");
        array[10] = new City(new Posn(737, 523), "Pretoria, South Africa");
        array[11] = new City(new Posn(767, 418), "Nairobi, Kenya");
        array[12] = new City(new Posn(697, 364), "N'Djamena, Chad");
        array[13] = new City(new Posn(752, 346), "Khartoum, Sudan");
        array[14] = new City(new Posn(803, 404), "Mogadishu, Somalia");
        array[15] = new City(new Posn(777, 449), "Dar es Salaam, Tanzania");
        array[16] = new City(new Posn(754, 524), "Maputo, Mozambique");
        array[17] = new City(new Posn(687, 454), "Luanda, Angola");
        array[18] = new City(new Posn(754, 417), "Kampala, Uganda");
        array[19] = new City(new Posn(750, 396), "Juba, South Sudan");
        return array;
    }

    private static City[] initOCCities() {
        City[] array = new City[20];
        array[0] = new City(new Posn(1263, 572), "Auckland, New Zealand");
        array[1] = new City(new Posn(1264, 592), "Wellington, New Zealand");
        array[2] = new City(new Posn(1261, 603), "Christchurch, New Zealand");
        array[3] = new City(new Posn(1157, 575), "Melbourne, Australia");
        array[4] = new City(new Posn(1179, 561), "Sydney, Australia");
        array[5] = new City(new Posn(1183, 534), "Brisbane, Australia");
        array[6] = new City(new Posn(1053, 548), "Perth, Australia");
        array[7] = new City(new Posn(1161, 452), "Port Moresby, Papua New Guinea");
        array[8] = new City(new Posn(1001, 403), "Kuala Lumpur, Malaysia");
        array[9] = new City(new Posn(990, 399), "Medan, Indonesia");
        array[10] = new City(new Posn(1008, 411),"Singapore, Singapore");
        array[11] = new City(new Posn(1017, 443), "Jakarta, Indonesia");
        array[12] = new City(new Posn(1045, 445), "Surabaya, Indonesia");
        array[13] = new City(new Posn(1055, 422), "Balikpapan, Indonesia");
        array[14] = new City(new Posn(1084, 385), "Davao, Philippines");
        array[15] = new City(new Posn(1069, 355), "Manila, Philippines");
        array[16] = new City(new Posn(22, 474), "Apia, Samoa");
        array[17] = new City(new Posn(1005, 394), "Dungun, Malaysia");
        array[18] = new City(new Posn(1060, 392), "Lahad Datu, Malaysia");
        array[19] = new City(new Posn(1036, 406), "Sibu, Malaysia");
        return array;
    }

    public static Continent[] initContinents() {
        City[] NACities = initNACities();
        City[] SACities = initSACities();
        City[] EUCities = initEUCities();
        City[] ASCities = initASCities();
        City[] AFCities = initAFCities();
        City[] OCCities = initOCCities();
        Continent[] array = new Continent[6];
        MissionSlot initMission = new MTMSlot(false);
        array[0] = new Continent(0, "North America", NACities, MapWorld.NAPos,
                                 new Posn(MapWorld.NAPos.x-40, MapWorld.NAPos.y-270), initMission);
        array[1] = new Continent(0, "South America", SACities, MapWorld.SAPos,
                                 new Posn(MapWorld.SAPos.x-235, MapWorld.SAPos.y-30), initMission);
        array[4] = new Continent(0, "Europe", EUCities, MapWorld.EUPos,
                                 new Posn(MapWorld.EUPos.x, MapWorld.EUPos.y-220), initMission);
        array[3] = new Continent(0, "Asia", ASCities, MapWorld.ASPos,
                                 new Posn(MapWorld.ASPos.x+130, MapWorld.ASPos.y-290), initMission);
        array[2] = new Continent(0, "Africa", AFCities, MapWorld.AFPos,
                                 new Posn(MapWorld.AFPos.x, MapWorld.AFPos.y+110), initMission);
        array[5] = new Continent(0, "Oceania", OCCities, MapWorld.OCPos,
                                 new Posn(MapWorld.OCPos.x-65, MapWorld.OCPos.y+100), initMission);
        return array;
    }

    public static void main(String[] args) {
        World world = new MapWorld(MapWorld.start, initContinents());
        //World world = new TestingWorld(initContinents(), 5, 19);
        //World world = new GameOverWorld();
        world.bigBang(MapWorld.screenHeight, MapWorld.screenWidth, .2);
    }

}
