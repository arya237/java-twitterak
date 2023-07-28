import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class user
{
    private String name;
    private String password;
    private String username;
    private String country;
    private String phonenumber;
    private String biography;
    private int number = 0;
    private Map < Integer , tweet> tweets = new HashMap <Integer, tweet>();
    
    void set_name(String name)
    {
        this.name = name;
    }


    void set_password(String password)
    {
        this.password = password;
    }

    void set_username(String username)
    {   
        if(username.charAt(0) == '@')
        this.username = username.substring(1);

        else this.username = username;
    }

    void set_country(String country)
    {
        this.country = country;
    }

    void set_phonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    void set_biography(String biography)
    {
        this.biography = biography;
    }

    
    void set_tweet(String post)
    {   
        tweet temp = new tweet();
        temp.set_post(post);
        
        add_number();
        tweets.put(this.number, temp);
    }
    
    void add_number()
    {
        this.number++;
    }

    void view_tweet( user target, int index)
    {  
        System.out.println(target.tweets.get(index).get_post());
        System.out.println("likes:" + target.tweets.get(index).getLikes());
        System.out.println("----------------------------------");
    }

    String get_username(){return username;}
    String get_passwrod(){return password;}
    String get_name(){return name;} 
    String get_contry(){return country;}
    String get_phonenumber(){return phonenumber;}
    String get_biography(){return biography;}

    void edit_tweet(int index)
    {   
        Scanner input =  new Scanner(System.in);
        System.out.print("enter your new tweet: ");
        tweets.get(index).set_post(input.nextLine());
    }

    void set_like(user currentuser, user target, int index)
    {
        target.tweets.get(index).setLikes(currentuser);
    }

    void retweet(user target, int index)
    {   
        String post =  target.get_username() + ':' + target.tweets.get(index).get_post();
        set_tweet(post);
    }

    void deletet_weet(int index)
    {
        tweets.remove(index);
    }

    boolean checknumber(int index)
    {
        if(tweets.containsKey(index))
        return true;

        else return false;
    }

}