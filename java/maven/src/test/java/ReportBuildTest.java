import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ydliu on 9/10/14.
 */
public class ReportBuildTest
{
    @Test
    public void should_report_with_sub_nodes()
    {
        // given

        Reportable sub1 = mock(Reportable.class);
        when(sub1.report()).thenReturn("Sub 1");
        when(sub1.getSubNodes()).thenReturn(new ArrayList<Reportable>());

        Reportable sub2 = mock(Reportable.class);
        when(sub2.report()).thenReturn("Sub 2");
        when(sub2.getSubNodes()).thenReturn(new ArrayList<Reportable>());

        ArrayList<Reportable> reportables = new ArrayList<Reportable>();
        reportables.add(sub1);
        reportables.add(sub2);

        Reportable root = mock(Reportable.class);
        when(root.report()).thenReturn("Top Level");
        when(root.getSubNodes()).thenReturn(reportables);

        // when

        String report = ReportBuilder.build(root);

        // then

        assertThat(report).isEqualTo(
                "Top Level\n" +
                "  Sub 1\n" +
                "  Sub 2\n");
    }
}
