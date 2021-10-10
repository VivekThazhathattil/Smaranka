public class Player {
    public String name;
    public int id;
    public char div;
    public int age;
    public double timeSpeedCards;
    public double timeSpokenNumbers;
    public double timeNumbers;
    public double timeImages;
    public double timeWords;
    public double timeNames;
    public double timeIntNames;

    public Player(){
        this.name = "default";
        this.id = 0;
        this.div = 'A';
        this.age = 25;
        this.timeSpeedCards = 60;
        this.timeSpokenNumbers = 60;
        this.timeNumbers = 60;
        this.timeImages = 60;
        this.timeWords = 60;
        this.timeNames  = 60;
        this.timeIntNames = 60;
    }

    public Player(String name, int id, char div, int age, double timeSpeedCards,
                  double timeSpokenNumbers, double timeImages, double timeWords,
                  double timeNumbers, double timeNames, double timeIntNames){
        this.name = name;
        this.id = id;
        this.div = div;
        this.age = age;
        this.timeSpeedCards = timeSpeedCards;
        this.timeSpokenNumbers = timeSpokenNumbers;
        this.timeNumbers = timeNumbers;
        this.timeImages = timeImages;
        this.timeWords = timeWords;
        this.timeNames  = timeNames;
        this.timeIntNames = timeIntNames;
    }
    public void setTimeSpeedCards(double _time){
        this.timeSpeedCards = _time;
    }
    public void setTimeSpokenNumbers(double _time){
        this.timeSpokenNumbers = _time;
    }
    public void setTimeImages(double _time){
        this.timeImages = _time;
    }
    public void setTimeWords(double _time){
        this.timeWords = _time;
    }
    public void setTimeNumbers(double _time){
        this.timeNumbers = _time;
    }
    public void setTimeNames(double _time){
        this.timeNames = _time;
    }
    public void setTimeIntNames(double _time){
        this.timeIntNames = _time;
    }
}
