import java.util.List;

public interface IProduceFrontend {
    
    public void runCommandLoop();//starts the command loop, terminates when user exits

    public void displayMainMenu(); // prints command options to System.out

    public void displayProduce(List<IProduce> produce);// displays a list of produce

    public void pluLookup();// reads word from System.in, displays results
   
}
