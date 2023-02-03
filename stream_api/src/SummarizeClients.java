import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public final class SummarizeClients {
    public static void main(String[] args) {

        int numClients = 0;
        int totalQuarterlySpend = 0;
        Long nextExpiration = null;
        Set<ZoneId> representedZoneIds = new HashSet<>();
        Map<Year, Long> contractsPerYear = new HashMap<>();

//        for (UdacisearchClient client : ClientStore.getClients()) {
//            numClients++;
//            totalQuarterlySpend += client.getQuarterlyBudget();
//            if (nextExpiration == null || client.getContractStart().plus(client.getContractLength())
//                    .isBefore(nextExpiration.getContractStart().plus(nextExpiration.getContractLength()))) {
//                nextExpiration = client;
//            }
//            for (ZoneId zone : client.getTimeZones()) {
//                representedZoneIds.add(zone);
//            }
//            contractsPerYear.compute(getContractYear(client), (k, v) -> (v == null) ? 1 : v + 1);
//        }

         totalQuarterlySpend=ClientStore.getClients().stream().mapToInt(UdacisearchClient::getQuarterlyBudget).sum();

        double averageBudget=ClientStore.getClients().stream().mapToDouble(UdacisearchClient::getQuarterlyBudget).average().orElse(0);

         nextExpiration=ClientStore.getClients().stream().min(Comparator.comparing(UdacisearchClient::getContractEnd)).map(UdacisearchClient::getId).orElse(-1L);

          representedZoneIds=
                 ClientStore.getClients().stream().flatMap(c->c.getTimeZones().stream()).collect(Collectors.toSet());

          contractsPerYear=
                  ClientStore.getClients().stream().collect(Collectors.groupingBy(SummarizeClients::getContractYear, Collectors.counting()));



        System.out.println("Num clients: " + numClients);
        System.out.println("Total quarterly spend: " + totalQuarterlySpend);
        System.out.println("Average budget: " + (double) totalQuarterlySpend / numClients);
        System.out.println("ID of next expiring contract: " + (nextExpiration == null ? -1 : nextExpiration));
        System.out.println("Client time zones: " + representedZoneIds);
        System.out.println("Contracts per year: " + contractsPerYear);
    }

    private static Year getContractYear(UdacisearchClient client) {
        LocalDate contractDate =
                LocalDate.ofInstant(client.getContractStart(), client.getTimeZones().get(0));
        return Year.of(contractDate.getYear());
    }
}
