package Server;

import org.omg.CORBA.ORB;

public class ServerImpl extends TaxesPOA {

    private ORB orb;

    /**
     * Calculates tax on a salary
     * @param salary The salary to be taxed
     * @param taxRate the tax rate
     * @return returns information of the salary and tax rate before tax,
     * then the salary after tax with the tax bracket used
     */
    @Override
    public String calculateTax(double salary, double taxRate) {
        double taxedSalary = salary - (salary * taxRate);

        int bracket = getBracket(taxRate * 100);

        String tax_result = String.format("%.2f", taxedSalary);

        return  "Before Applying Tax:\n" +
                "Salary: $"+ salary + "\n" +
                "Tax Rate: "+ taxRate * 100+ "%\n\n" +
                "After Applying Tax using Bracket " + bracket + ":\n"+
                "Salary: $" + tax_result;
    }

    @Override
    public void shutdown() {
        this.orb.shutdown(false);
    }

    public void setORB(ORB orb){
        this.orb = orb;
    }

    private int getBracket(double taxRate){

        if(taxRate <= 5) return 1;

        else if(taxRate >= 6 && taxRate <= 10) return 2;

        else if(taxRate >= 11 && taxRate <= 15) return 3;

        else if(taxRate >= 16 && taxRate <= 20) return 4;

        else if(taxRate > 20) return 5;

        else return 1;

    }
}
