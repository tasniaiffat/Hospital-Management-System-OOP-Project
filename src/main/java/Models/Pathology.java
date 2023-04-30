package Models;

import Models.ClassHierarchy.PathologyInterface;
import Models.ClassHierarchy.Service;

public class Pathology extends Service implements PathologyInterface {
    private String testName;
    private String testDescription;
    private String testPrice;

    private String results;

    public boolean updateTestResults(){
        return true;
    }

    @Override
    public boolean provideService() {
        return super.provideService();
    }
}
