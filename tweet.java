import java.util.Vector;

public class tweet
{
    private String post;
    private Vector <user> likes = new Vector<user>();

    
    public void set_post(String post)
    {
        this.post = post;
    }
    
    public void setLikes(user currentuser)
    {   if(!(likes.contains(currentuser)) )
        likes.addElement(currentuser);  
        
        else System.out.println("ypu liked before!");
    }
    
    public int getLikes()
    {
        return likes.size();
    }
    
    String get_post(){return post;}
    
}