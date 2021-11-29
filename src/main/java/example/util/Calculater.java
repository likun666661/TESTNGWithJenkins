package example.util;

import example.entity.baggage;
import example.entity.innerBaggage;
import example.entity.specialBaggage;

import java.util.*;

public class Calculater {
    private HashMap<String, List<Integer>> prices;
    private HashMap<String, List<Integer>> extraPrices;
    private HashMap<String, Integer> freeTimes;
    private HashSet<String> specialUsers;//国内的vip用户列别
    private HashSet<String>passengers;
    private HashMap<String,HashMap<Integer,Integer>>specialBaggagePrice;
    public Calculater(){
        prices = new HashMap<>();
        Integer[] a = {380, 980, 980, 1400};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
        prices.put("国外区域一", list);
        Integer[] b = {280, 690, 690, 1100};
        list = new ArrayList<Integer>(Arrays.asList(b));
        prices.put("国外区域二", list);
        Integer[] c = {520, 520, 520, 520};
        list = new ArrayList<Integer>(Arrays.asList(c));
        prices.put("国外区域三", list);
        Integer[] d = {690, 1040, 1040, 2050};
        list = new ArrayList<Integer>(Arrays.asList(d));
        prices.put("国外区域四", list);
        Integer[] e = {210, 520, 830};
        list = new ArrayList<Integer>(Arrays.asList(e));
        prices.put("国外区域五", list);
        freeTimes = new HashMap<>();

        extraPrices = new HashMap<>();
        Integer[] a0 = {1400, 2000, 3000};
        list = new ArrayList<Integer>(Arrays.asList(a0));
        extraPrices.put("国外区域一", list);
        Integer[] b0 = {1100, 1590, 1590};
        list = new ArrayList<Integer>(Arrays.asList(b0));
        extraPrices.put("国外区域二", list);
        Integer[] c0 = {1170, 1590, 1590};
        list = new ArrayList<Integer>(Arrays.asList(c0));
        extraPrices.put("国外区域三", list);
        Integer[] d0 = {1380, 1590, 1590};
        list = new ArrayList<Integer>(Arrays.asList(d0));
        extraPrices.put("国外区域四", list);
        Integer[] e0 = {830, 1100, 1590};
        list = new ArrayList<Integer>(Arrays.asList(e0));
        extraPrices.put("国外区域五", list);

        freeTimes.put("头等舱", 2);
        freeTimes.put("公务舱", 2);
        freeTimes.put("悦享经济舱", 2);
        freeTimes.put("超级经济舱", 2);
        freeTimes.put("经济舱", 1);

        specialUsers=new HashSet<>();
        String []sp={"凤凰知音终身白金卡", "凤凰知音白金卡", "凤凰知音金卡", "凤凰知音银卡", "星空联盟金卡", "否"};
        Collections.addAll(specialUsers, sp);

        passengers=new HashSet<>();
        String[]ps={"成人","儿童","婴儿"};
        Collections.addAll(passengers,ps);

        specialBaggagePrice=new HashMap<>();
        specialBaggagePrice.put("运动器械器具种类一",new HashMap<Integer,Integer>());
        specialBaggagePrice.put("运动器械器具种类二",new HashMap<Integer,Integer>());
        specialBaggagePrice.put("小型电器或仪器，媒体设备",new HashMap<Integer,Integer>());
        specialBaggagePrice.put("枪支",new HashMap<Integer,Integer>());
        specialBaggagePrice.put("子弹",new HashMap<Integer,Integer>());
        specialBaggagePrice.put("小动物",new HashMap<Integer,Integer>());

        Map<Integer,Integer>map=specialBaggagePrice.get("运动器械器具种类一");
        map.put(2,2600);
        map.put(23,3900);
        map.put(32,5200);
        map.put(45,-1);

        map=specialBaggagePrice.get("运动器械器具种类二");
        map.put(2,1300);
        map.put(23,2600);
        map.put(32,3900);
        map.put(45,-1);

        map=specialBaggagePrice.get("小型电器或仪器，媒体设备");
        map.put(2,490);
        map.put(23,3900);
        map.put(32,-1);

        map=specialBaggagePrice.get("枪支");
        map.put(2,1300);
        map.put(23,2600);
        map.put(32,-1);

        map=specialBaggagePrice.get("子弹");
        map.put(2,1300);
        map.put(5,-1);

        map=specialBaggagePrice.get("小动物");
        map.put(2,3900);
        map.put(8,5200);
        map.put(23,7800);
        map.put(32,-1);
    }
    public int entryOfCalculate(baggage[] baggages, String airportClass, String area){
        if(!prices.containsKey(area)){
            return -1;
        }
        if(!freeTimes.containsKey(airportClass)){
            return -1;
        }
        if(baggages==null||baggages.length==0){
            return -1;
        }
        return calculate(baggages,airportClass,area,freeTimes.get(airportClass));
    }
    int calculate(baggage[] baggages, String airportClass, String area, int freeTime) {
        int i = 0;
        int result = 0;
        List<Integer> priceList = prices.get(area);
        List<Integer> extraPriceList = extraPrices.get(area);
        for (; i < baggages.length; ++i) {
            if (i < freeTime) {
                result += calculateHelp(baggages[i], airportClass, priceList.get(0), priceList.get(1), priceList.get(2), priceList.get(3));
            } else {
                if (i - freeTime <= 2) {
                    result += extraPriceList.get(i - freeTime) + calculateHelp(baggages[i], airportClass, priceList.get(0), priceList.get(1), priceList.get(2), priceList.get(3));
                } else
                    result += extraPriceList.get(2) + calculateHelp(baggages[i], airportClass, priceList.get(0), priceList.get(1), priceList.get(2), priceList.get(3));
            }
        }
        return result;
    }
    public int calculateHelp(baggage baggage0, String airportClass, int price1, int price2, int price3, int price4) {


        int result = 0;

        if (airportClass.equals("头等舱") || airportClass.equals("公务舱")) {
            if (baggage0.getPackingSize() > 158 && baggage0.getPackingSize() <= 203) {
                result = price4;
            }
        }
        if (airportClass.equals("经济舱") || airportClass.equals("悦享经济舱") || airportClass.equals("超级经济舱")) {
            if (baggage0.getPackingSize() >= 60 && baggage0.getPackingSize() <= 158) {
                if (baggage0.getWeight() > 23 && baggage0.getWeight() <= 28) {
                    result = price1;
                } else if (baggage0.getWeight() > 28 && baggage0.getWeight() <= 32) {
                    result = price2;
                }


            }
            if (baggage0.getPackingSize() > 158 && baggage0.getPackingSize() <= 203) {
                if (baggage0.getWeight() > 2 && baggage0.getWeight() <= 23) {
                    result = price3;
                } else if (baggage0.getWeight() > 23 && baggage0.getWeight() <= 32) {
                    result = price4;
                }
            }
        }

        return result;
    }
    public double entryOfCalculate(innerBaggage[]innerBaggages, String specialUser, String passenger, String airportClass){
        if(innerBaggages==null||innerBaggages.length==0){
            return -1.0;
        }
        if(specialUser==null||!specialUsers.contains(specialUser)){
            return -1.0;
        }
        if(passenger==null||!passengers.contains(passenger)){
            return -1.0;
        }
        if(airportClass==null||!freeTimes.containsKey(airportClass)){
            return -1.0;
        }
        return calculate(innerBaggages,specialUser,passenger,airportClass);
    }
    public double calculate(innerBaggage[] innerBaggages, String specialUser, String passenger, String airportClass){
        double heightSum = 0;
        for (int i = 0; i < innerBaggages.length; ++i) {
            heightSum += innerBaggages[i].getWeight();
        }
        heightSum =  calculateHelp(heightSum,specialUser,passenger,airportClass);
        return heightSum;
    }
    public double calculateHelp(double height, String specialUser, String passenger, String airportClass) {
        if (passenger.equals("成人") || passenger.equals("儿童")) {
            if (airportClass.equals("头等舱")) {
                height -= 40;
            } else if (airportClass.equals("公务舱")) {
                height -= 30;
            } else {
                height -= 20;
            }
        } else {
            height -= 10;
        }

        if (specialUser.equals("凤凰知音终身白金卡") || specialUser.equals("凤凰知音白金卡")) {
            height -= 30;
        } else if (specialUser.equals("凤凰知音金卡") || specialUser.equals("凤凰知音银卡")) {
            height -= 20;
        } else if (specialUser.equals("星空联盟金卡")) {
            height -= 20;
        } else {
            height -= 0;
        }

        if (height < 0) {
            height = 0;
        }
        return height;

    }

    public int entryOfCalculate(specialBaggage[]specialBaggages){
        if(specialBaggages==null||specialBaggages.length==0){
            return -1;
        }
        return calculate(specialBaggages);

    }

    public int calculate(specialBaggage[]specialBaggages){
        int price=0;
        for(int i=0;i<specialBaggages.length;++i){
            price+=calculateHelp(specialBaggages[i]);
        }
        return price;
    }
    public int calculateHelp(specialBaggage specialBaggage0){

        HashMap<Integer,Integer>map=specialBaggagePrice.get(specialBaggage0.getKind());
        int[] weights=new int[map.size()];
        int[]prices=new int[map.size()];
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();

        int i=0;
        while(entries.hasNext()){
            Map.Entry<Integer, Integer> entry = entries.next();
            weights[i]=entry.getKey();
            prices[i]=entry.getValue();
            ++i;
        }
        Arrays.sort(weights);
        Arrays.sort(prices);
        for(i=0;i<weights.length-1;++i){
            if(weights[i]<specialBaggage0.getWeight()&&specialBaggage0.getWeight()<=weights[i+1]){
                return prices[i+1];
            }
        }
        return -1;

    }
}
