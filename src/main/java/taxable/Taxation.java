package taxable;

import java.util.Arrays;
import java.util.List;

class Taxable {}
class Individual extends Taxable {}
class Corporation extends Taxable {}

public class Taxation {
    public static void computeTaxes(Taxable t) { }
    public static void computeTaxes(List<? extends Taxable> lt) {
//        lt.forEach(Taxation::computeTaxes);
        for (Taxable t : lt) {
            computeTaxes(t);
        }
//        lt.add(new Taxable());
    }

    public static void addClients(List<? super Individual> li) {
        li.add(new Individual());
//        Individual i = li.get(0);
    }

    public static void main(String[] args) {
        List<Taxable> lt = Arrays.asList();
        computeTaxes(lt);
        addClients(lt);

        List<Individual> li = Arrays.asList();
        addClients(li);
        computeTaxes(li);


    }
}
