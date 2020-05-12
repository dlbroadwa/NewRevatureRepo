package tests;

import data.SQLDatabaseEmployees;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class EmployeeTest {
    @Mock SQLDatabaseEmployees mockEmployee;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    //services to be added
}
