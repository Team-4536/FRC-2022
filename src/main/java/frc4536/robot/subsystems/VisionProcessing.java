package frc4536.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionProcessing {

NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);
protected void execute() {
  
    SmartDashboard.putNumber("Limelight x", x);
    SmartDashboard.putNumber("Limelight y", y);
    SmartDashboard.putNumber("Limelight Area", area);

}
}