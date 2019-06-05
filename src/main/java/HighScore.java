import java.util.Vector;
import java.lang.String;

public class HighScore
{
    Vector<Element> position = new Vector<>(3);

    public HighScore()
    {
        position.add(new Element("Pro", 15));
        position.add(new Element("Noob", 10));
        position.add(new Element("Newbie", 5));
    }

    public void addToHighScore(String nick, int sc)
    {
        if(sc <= position.get(2).score)
            return;
        else if(sc <= position.get(1).score)
            position.set(2,new Element(nick,sc));
        else if(sc <= position.get(0).score)
        {    position.set(2,position.get(1));
            position.set(1,new Element(nick,sc));}
        else
        {   position.set(2,position.get(1));
            position.set(1,position.get(0));
            position.set(0,new Element(nick,sc));}
    }



    class Element
    {
        public int score;
        public String nickname;
        private Element(String nn, int sc)
        {
            score = sc;
            nickname = nn;
        }
    }
}
