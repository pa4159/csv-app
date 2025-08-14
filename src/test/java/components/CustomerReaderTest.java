package components;

import com.test.app.model.Customer;
import com.test.app.components.CustomerReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerReaderTest {
    CustomerReader customerReader = new CustomerReader();

    @AfterEach
    void clearDown() throws IOException {
        Files.delete(Path.of("data.csv"));
    }

    @Test
    void givenValidCsvRow_whenParsed_thenAllFieldsMapped() throws IOException {
        Customer expectedCustomer = new Customer();
        expectedCustomer.setReference("CustomerRef1");
        expectedCustomer.setName("CustomerName1");
        expectedCustomer.setAddressLineOne("AL1");
        expectedCustomer.setAddressLineTwo("AL2");
        expectedCustomer.setTown("T1");
        expectedCustomer.setCounty("County1");
        expectedCustomer.setCountry("Country1");
        expectedCustomer.setPostcode("111 111");

        File file = createCsvFile("CustomerRef1,CustomerName1,AL1,AL2,T1,County1,Country1,111 111");

        List<Customer> customers = customerReader.readFile(file);

        assertThat(customers.size()).isEqualTo(1);
        assertThat(customers.get(0)).isEqualTo(expectedCustomer);
    }

    @Test
    void givenValidCsvFile_whenParsed_thenListOfCustomersReturned() throws IOException {
        File file = createCsvFile("CustomerRef1,CustomerName1,AL11,AL12,T1,C1,Country1,111 111",
                "CustomerRef2,CustomerName2,AL21,AL22,T2,C2,Country2,222 222");

        List<Customer> customers = customerReader.readFile(file);

        assertThat(customers.size()).isEqualTo(2);
    }

    @Test
    void givenDuplicatedCustomerReference_whenParsed_thenDuplicateShouldBeRemoved() throws IOException {
        File file = createCsvFile("CustomerRef1,CustomerName1,AL11,AL12,T1,C1,Country1,111 111",
                "CustomerRef1,CustomerName2,AL21,AL22,T2,C2,Country2,222 222");

        List<Customer> customers = customerReader.readFile(file);

        assertThat(customers.size()).isEqualTo(1);
    }

    @Test
    void givenEmptyCustomerReference_whenParsed_thenCustomerShouldNotBePresent() throws IOException {
        File file = createCsvFile(",CustomerName1,AL11,AL12,T1,C1,Country1,111 111");

        List<Customer> customers = customerReader.readFile(file);

        assertThat(customers.size()).isEqualTo(0);
    }

    private File createCsvFile(String... lines) throws IOException {
        File file = new File("data.csv");

        if (!file.exists()) file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (String line : lines) {
            bw.newLine();
            bw.write(line);
        }

        bw.close();

        return file;
    }
}
