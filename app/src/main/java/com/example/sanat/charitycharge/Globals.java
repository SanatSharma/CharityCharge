package com.example.sanat.charitycharge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sanat on 1/27/2018.
 */

public class Globals{
    private static Globals instance;

    // Global variable
    private HashMap<Integer, Charity> charities = createMap();
    private ArrayList<String> charityNames = new ArrayList<String>();


    // Restrict the constructor from being instantiated
    private Globals(){}

    public HashMap getData(){
        return this.charities;
    }

    public ArrayList getNames(){
        return this.charityNames;
    }

    public Charity getCharity(int id){
        return this.charities.get(id);
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    private HashMap<Integer, Charity> createMap() {
         HashMap<Integer, Charity> c = new HashMap<Integer, Charity>();
         c.put(0, new Charity("United Way Worldwide", "https://www.unitedway.org/assets/img/logo.svg", "https://www.unitedway.org/",
                 "United Way improves lives by mobilizing the caring power of communities around the world to advance the common good."));

         charityNames.add("United Way Worldwide");

         c.put(1, new Charity("Task Force for Global Health", "https://specials-images.forbesimg.com/imageserve/5852bc65a7ea431d601b17b9/200x200.jpg?background=000000&cropX1=0&cropX2=1092&cropY1=39&cropY2=1130",
                 "https://www.taskforce.org/", "The Task Force for Global Health is an independent, nongovernmental organization based in Decatur, GA, USA, with field offices in Addis Ababa, Ethiopia, and Guatemala City, Guatemala. We improve health conditions for vulnerable populations around the world, especially people living in poverty. Our programs focus on controlling and eliminating debilitating diseases and building durable systems that protect and promote health."));

        charityNames.add(("Task Force for Global Health"));

         c.put(2, new Charity("Feeding America", "http://www.feedingamerica.org/assets/images/logo.png", "http://www.feedingamerica.org/",
                 "In a country that wastes billions of pounds of food each year, it's almost shocking that anyone in America goes hungry. Yet every day, there are millions of children and adults who do not get the meals they need to thrive. We work to get nourishing food – from farmers, manufacturers, and retailers – to people in need. At the same time, we also seek to help the people we serve build a path to a brighter, food-secure future."));

         charityNames.add("Feeding America");

         c.put(3, new Charity("The Salvation Army", "https://s3.amazonaws.com/cache.salvationarmy.org/resources/img/ihq-logo.jpg","https://www.salvationarmy.org/",
                 "The Salvation Army is an integral part of the Christian Church, although distinctive in government and practice. The Army’s doctrine follows the mainstream of Christian belief and its articles of faith emphasise God’s saving purposes. Its objects are ‘the advancement of the Christian religion… of education, the relief of poverty, and other charitable objects beneficial to society or the community of mankind as a whole.’"));

         charityNames.add("The Salvation Army");

         c.put(4, new Charity("American Red Cross", "http://www.redcross.org/images/redcross-logo.png", "http://www.redcross.org/",
                         "Each day, thousands of people – people just like you – provide compassionate care to those in need. Our network of generous donors, volunteers and employees share a mission of preventing and relieving suffering, here at home and around the world.\n" +
                         "We roll up our sleeves and donate time, money and blood. We learn or teach life-saving skills so our communities can be better prepared when the need arises. We do this every day because the Red Cross is needed - every day."));

         charityNames.add("American Red Cross");

         return c;

    }

    private class Charity{
        String name;
        String image;
        String website;
        String description;

        Charity(String name, String image, String website, String description){
            this.name = name;
            this.image = image;
            this.website = website;
            this.description = description;
        }
    }

}