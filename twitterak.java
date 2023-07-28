import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class twitterak
{
    private Map <String , user> users = new HashMap<String, user>();
    private user currentuser = new user();

    void run()
    {   
        Scanner input = new Scanner(System.in);

        System.out.println(">signup");
        System.out.println(">login");
       

        if(input.next().equals("signup"))
        {
            signup();
        }

        else login();

    }

    void signup()
    {   
        Scanner input = new Scanner(System.in); 
        System.out.print("enter name: ");
        currentuser.set_name(input.next());
        input.nextLine();
        
        System.out.print("enter username: ");
        String username = input.nextLine();
        
        while(!check_username(username))
        {   
            username = input.nextLine();
        }
        
        currentuser.set_username(username);

        System.out.print("enter your password: ");
        String password = input.nextLine();

        while(!check_password(password))
        {
            password = input.nextLine();
        }

        currentuser.set_password(password);

        System.out.print("enter your country: ");
        currentuser.set_country(input.nextLine());

        System.out.print("enter your biography: ");
        currentuser.set_biography(input.nextLine());

        System.out.print("enter your phonenumber: ");
        currentuser.set_phonenumber(input.nextLine());

    
        users.put(currentuser.get_username(), currentuser);
        System.out.println("signup is successful!" + '\n');
        inaccount(currentuser);
        

    }

    void login()
    {   
        Scanner input = new Scanner(System.in); 
        System.out.print("enter username: ");
        String username = input.nextLine();

        if(users.containsKey(username))
        {
            user temp = new user();

            temp = users.get(username);

            
            System.out.print("enter your password: ");
            if(temp.get_passwrod().equals(input.nextLine()))
            {   
                System.out.println("login is successful!" + '\n');
                
                inaccount(users.get(username));
            }

            else
            {
                System.out.println("password is invalid!");
                run();
            } 
                
        }

        else
        {
            System.out.println("username not found!");
            run();
        } 
    }

    void inaccount(user currentuser)
    {   
        System.out.println("if you need help enter help!");
        
        while(true)
        {   
            System.out.println();
            Scanner in = new Scanner(System.in);
            System.out.println('@' + currentuser.get_username());
            
            String choice = in.next();
            in.nextLine();
    
            if(choice.equals("tweet"))
            {
                System.out.println("enter your tweet: ");
                currentuser.set_tweet(in.nextLine());
            }
    
            if(choice.equals("viewtweet"))
            {   
                System.out.println("enter the username: ");
                String username = in.nextLine();
                
                if(username.charAt(0) == '@')
                {
                    username = username.substring(1);
                }

                System.out.print("which tweet you want to view? ");
                int tweetnumber = in.nextInt();

                if(check(users.get(username), tweetnumber))
                {
                    currentuser.view_tweet(users.get(username), tweetnumber);
                }

                else System.out.println("user or tweet not found!");

                in.nextLine();
            }

            if(choice.equals("edittweet"))
            {   
                System.out.println("which tweet you want to edit? ");
                int tweetnumber = in.nextInt();

                if(currentuser.checknumber(tweetnumber))
                {
                    currentuser.edit_tweet(tweetnumber);
                }

                else System.out.println("tweet not exsit!");

                in.nextLine();
            }

            if(choice.equals("like"))
            {
                System.out.println("enter the username: ");
                String username = in.nextLine();
                
                if(username.charAt(0) == '@')
                {
                    username = username.substring(1);
                }

                System.out.print("which tweet you want to like? ");
                int tweetnumber = in.nextInt();
                
                if(check(users.get(username), tweetnumber))
                {
                    currentuser.set_like(currentuser, users.get(username), tweetnumber);
                }

                else System.out.println("user or tweet not found!");

                in.nextLine();
            }

            if(choice.equals("editprofile"))
            {
                edit_profile();
            }

            if(choice.equals("deletetweet"))
            {   
                System.out.println("which tweet you want to delete? ");
                int tweetnumber = in.nextInt();
                currentuser.deletet_weet(tweetnumber);
                System.out.println("tweet deleted!");
                
                in.nextLine();
            }

            if(choice.equals("deleteaccount"))
            {
                System.out.print("are you sure? y/n ");
                if(in.nextLine().equals("y"))
                {
                    delete_account();
                }
                run();
            }

            
            if(choice.equals("exit"))
            {   
                System.exit(0);
            }
            
            if(choice.equals("logout"))
            {
                run();
            }

            if(choice.equals("retweet"))
            {
                System.out.println("enter the username: ");
                String username = in.nextLine();
                
                if(username.charAt(0) == '@')
                {
                    username = username.substring(1);
                }

                System.out.print("which tweet you want to retweet? ");
                int tweetnumber = in.nextInt();

                if(check(users.get(username), tweetnumber))
                {   
                    currentuser.retweet(users.get(username), tweetnumber);
                }

                else System.out.println("user or tweet not found!");

                in.nextLine();   
            }

            if(choice.equals("help"))
            {
                help();
            }
        }
    }
    
    void edit_profile()
    {   
        Scanner input = new Scanner(System.in);
        System.out.println("wich part you want to edit? ");
        String choise = input.nextLine();

        if(choise.equals("username"))
        {
            users.remove(currentuser.get_username());
            System.out.println("enter new username: ");
            currentuser.set_username(input.nextLine());

            users.put(currentuser.get_username(), currentuser);
            System.out.println("username changed succsessfuly!");

        }

        if(choise.equals("password"))
        {
            System.out.print("enter new password: ");
            currentuser.set_password(input.nextLine());
            System.out.println("password changed succsessfuly!");

        }        
    }

    void delete_account()
    {
        users.remove(currentuser.get_username());
        System.out.println("deleteaccount was succsessful!");
        run();
    }

    void help()
    {
        System.out.println(">tweet" + '\n' + ">viewtweet" + '\n' + ">edittweet"
        + '\n' + ">like" + '\n' + ">editprofile" + '\n' + ">retweet" + '\n' + ">deleteaccount" +
        '\n' + ">deletetweet" + '\n' + ">exit" + '\n' + ">logout");
    }


    boolean check(user target, int index)
    {
        if(users.containsValue(target))
        {
            if(target.checknumber(index))
            return true;

            else return false;
        }

        else return false;
    }

    boolean check_username(String username)
    {
        try
        {
            if(username.length() <= 5 || users.containsKey(username))
            {
                throw new AssertionError("invalid username! please enter another username: ");
            }
        }

        catch(AssertionError e)
        {
            System.out.print(e.getMessage());
            return false;
        }

        return true;
    }

    boolean check_password(String password)
    {   
        boolean charflag = false , numflag = false, lowflag = false, upflag = false; 

        for(int i = 0; i < password.length(); i++)
        {
            if( ((int)password.charAt(i) >= 33 && (int)password.charAt(i) <= 47) ||
            (int)password.charAt(i) > 57 && (int)password.charAt(i) <= 64)
            {
                charflag = true;
            }

            if((int)password.charAt(i) >= 65 && (int)password.charAt(i) <= 90)
            {
                upflag = true;
            }

            if((int)password.charAt(i) >= 97 && (int)password.charAt(i) <= 122)
            {
                lowflag = true;
            }

            if((int)password.charAt(i) >= 48 && (int)password.charAt(i) <= 57)
            {
                numflag = true;
            }
        }

    
        if(numflag && charflag && lowflag && upflag)
        {
            return true;
        }
        

        else 
        {
            try
            {
                throw new SecurityException("password is too weak! try again: ");
            }

            catch(SecurityException e)
            {
                System.out.print(e.getMessage());
                return false;
            }
        }
    }
    
}