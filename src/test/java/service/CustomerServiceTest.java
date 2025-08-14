package service;

import com.test.app.components.CustomerReader;
import com.test.app.service.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CustomerServiceTest {

    @InjectMocks
    CustomerService service;

    @Mock
    CustomerReader reader;
}
