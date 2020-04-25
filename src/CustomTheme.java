import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class CustomTheme extends DefaultMetalTheme {
 
    public String getName() { return "Toms"; }
 
    private final ColorUIResource primary1 = new ColorUIResource(245, 233, 67); //gold - border, corners, inside icons
    private final ColorUIResource primary2 = new ColorUIResource(255, 255, 255); //white - border, around button
    private final ColorUIResource primary3 = new ColorUIResource(24, 42, 206); //navy blue- top
 
    protected ColorUIResource getPrimary1() { return primary1; }
    protected ColorUIResource getPrimary2() { return primary2; }
    protected ColorUIResource getPrimary3() { return primary3; }
 
}