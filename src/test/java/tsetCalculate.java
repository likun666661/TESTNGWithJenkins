import example.entity.baggage;
import example.entity.innerBaggage;
import example.entity.specialBaggage;
import example.util.Calculater;
import org.testng.Assert;
import org.testng.annotations.Test;

public class tsetCalculate {
    private Calculater calculater=new Calculater();
    
    //非接口函数，下面函数的参数已经经历过了接口函数的测试，本身就是正确的。要做的是白盒覆盖
    @Test
    public void testCalculateHelp0(){
        String airportClass="头等舱";
        baggage baggage0=new baggage();
        baggage0.setWeight(28);
        baggage0.setPackingSize(167);
        baggage baggage1=new baggage();
        baggage1.setPackingSize(134);
        baggage1.setWeight(25);
        baggage baggage2=new baggage();
        baggage2.setPackingSize(138);
        baggage2.setWeight(29);
        baggage baggage3=new baggage();
        baggage3.setWeight(16);
        baggage3.setPackingSize(167);
        baggage baggage4=new baggage();
        baggage4.setWeight(29);
        baggage4.setPackingSize(167);
        Assert.assertEquals(1400,calculater.calculateHelp(baggage0,airportClass,380,980,980,1400));
        Assert.assertEquals(380,calculater.calculateHelp(baggage1,"经济舱",380,980,980,1400));
        Assert.assertEquals(980,calculater.calculateHelp(baggage2,"经济舱",380,980,980,1400));
        Assert.assertEquals(980,calculater.calculateHelp(baggage3,"经济舱",380,980,980,1400));
        Assert.assertEquals(1400,calculater.calculateHelp(baggage4,"经济舱",380,980,980,1400));
    }

    @Test
    public void testEntryOfCalculate1(){
        innerBaggage[]innerBaggages=new innerBaggage[2];//1
        for(int i=0;i<innerBaggages.length;++i){
            innerBaggages[i]=new innerBaggage();
        }
        innerBaggages[0].setWeight(120);
        innerBaggages[0].setHeight(50);
        innerBaggages[0].setLength(50);
        innerBaggages[0].setWidth(50);
        innerBaggages[1].setWeight(120);
        innerBaggages[1].setHeight(50);
        innerBaggages[1].setLength(50);
        innerBaggages[1].setWidth(50);
        String airportClass="头等舱";//7
        String passenger="成人";//5
        String special="凤凰知音白金卡";//3

        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,special,passenger,"其它"));//1,3,5,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,special,"其它",airportClass));//1,3,6,7
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,special,"其它","其它"));//1,3,6,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,"其它",passenger,airportClass));//1,4,5,7
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,"其它",passenger,"其它"));//1,4,5,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,"其它","其它",airportClass));//1,4,5,7
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(innerBaggages,"其它","其它","其它"));//1,4,5,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,special,passenger,"其它"));//1,3,5,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,special,"其它",airportClass));//1,3,6,7
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,special,"其它","其它"));//1,3,6,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,"其它",passenger,airportClass));//1,4,5,7
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,"其它",passenger,"其它"));//1,4,5,8
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,"其它","其它",airportClass));//1,4,5,7
        Assert.assertEquals(-1.0, calculater.entryOfCalculate(null,"其它","其它","其它"));//1,4,5,8
        Assert.assertEquals(-1.0,calculater.entryOfCalculate(null,special,passenger,airportClass));
        Assert.assertEquals(170,(int)calculater.entryOfCalculate(innerBaggages,special,passenger,airportClass));
    }

    @Test
    public void testCalculateHelp1(){
        Assert.assertEquals(0, (int)calculater.calculateHelp(40,"凤凰知音白金卡","成人","头等舱"));
        Assert.assertEquals(40, (int)calculater.calculateHelp(90,"凤凰知音金卡","成人","公务舱"));
        Assert.assertEquals(50, (int)calculater.calculateHelp(90,"星空联盟金卡","成人","经济舱"));
        Assert.assertEquals(20, (int)calculater.calculateHelp(30,"否","婴儿","头等舱"));
    }

    @Test
    public void testCalculate1(){
        innerBaggage[]innerBaggages=new innerBaggage[4];
        for(int i=0;i<4;++i){
            innerBaggages[i]=new innerBaggage();
            innerBaggages[i].setWidth(50);
            innerBaggages[i].setHeight(50);
            innerBaggages[i].setLength(50);
            innerBaggages[i].setWeight(40);
        }
        Assert.assertEquals(90, (int)calculater.calculate(innerBaggages,"凤凰知音白金卡","成人","头等舱"));
    }
    @Test
    public void testcalculateHelp2(){
        specialBaggage[]baggages=new specialBaggage[6];
        baggages[0]=new specialBaggage();
        baggages[0].setWeight(17);
        baggages[0].setKind("运动器械器具种类一");
        baggages[1]=new specialBaggage();
        baggages[1].setWeight(27);
        baggages[1].setKind("运动器械器具种类一");
        baggages[2]=new specialBaggage();
        baggages[2].setWeight(37);
        baggages[2].setKind("运动器械器具种类一");
        baggages[3]=new specialBaggage();
        baggages[3].setWeight(3);
        baggages[3].setKind("子弹");
        baggages[4]=new specialBaggage();
        baggages[4].setWeight(17);
        baggages[4].setKind("枪支");
        baggages[5]=new specialBaggage();
        baggages[5].setWeight(27);
        baggages[5].setKind("枪支");
        Assert.assertEquals(2600,calculater.calculateHelp(baggages[0]));
        Assert.assertEquals(3900,calculater.calculateHelp(baggages[1]));
        Assert.assertEquals(5200,calculater.calculateHelp(baggages[2]));
        Assert.assertEquals(1300,calculater.calculateHelp(baggages[3]));
        Assert.assertEquals(1300,calculater.calculateHelp(baggages[4]));
        Assert.assertEquals(2600,calculater.calculateHelp(baggages[5]));
    }
}
