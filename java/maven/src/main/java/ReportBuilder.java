/**
 * Created by ydliu on 9/10/14.
 */
public class ReportBuilder
{
    private static String buildWithIndent(Reportable root, String indent)
    {
        String report = String.format("%s%s\n", indent, root.report());
        indent += "  ";
        for (Reportable r : root.getSubNodes())
        {
            report += buildWithIndent(r, indent);
        }
        return report;
    }

    public static String build(Reportable root)
    {
        return buildWithIndent(root, "");
    }
}
