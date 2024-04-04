package com.example.mongojava.nativejava;

import com.example.mongojava.nativejava.models.Address;
import com.example.mongojava.nativejava.models.Company;
import com.example.mongojava.nativejava.models.Geo;
import com.example.mongojava.nativejava.models.User;
import com.example.mongojava.nativejava.repo.UserRepository;
import com.example.mongojava.nativejava.repo.UserRepositoryImpl;

public class Runner {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
//        findById(userRepository);
//        save(userRepository);
//        edit(userRepository);
//        userRepository.findAll().forEach(System.out::println);
//        userRepository.findAllByZipCodeEndWith("4", "9").forEach(System.out::println);
//        userRepository.findAllByLatitudeWithNegative().forEach(System.out::println);
//        userRepository.findAllByWebSiteEndWithDomain("com").forEach(System.out::println);
        boolean isDone = userRepository.editOfCatchPhraseAndLongitude(
                "660b807c7280577db8c72a45",
                "Example phrase",
                "-21.45678"
        );
        System.out.println("isDone = " + isDone);
    }

    private static void edit(UserRepository userRepository) {
        userRepository.findById("660d15399184da679acd6140")
                .ifPresent(user -> {
                    user.setName("John Smith");
                    Company company = user.getCompany();
                    company.setName("Microsoft");
                    Address address = user.getAddress();
                    address.setCity("Samarkand");
                    user.setCompany(company);
                    System.out.println("userRepository.edit(user) = " + userRepository.edit(user));
                });
    }

    private static void save(UserRepository userRepository) {
        User user = User.builder()
                .name("Johnny Doe")
                .username("johnny")
                .email("john@gmail.com")
                .id(204)
                .address(Address.builder()
                        .city("Tashkent")
                        .suite("Apt. 34")
                        .street("Beruni")
                        .zipcode("34563-2145")
                        .geo(Geo.builder()
                                .lat("-35.1136")
                                .lng("-24.4631")
                                .build())
                        .build())
                .phone("+998901234567")
                .website("noreply.com")
                .company(Company.builder()
                        .name("Google")
                        .catchPhrase("Something phrase")
                        .bs("new bs value")
                        .build())
                .build();
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
    }

    private static void findById(UserRepository userRepository) {
        userRepository.findById("660d15399184da679acd6140")
                .ifPresentOrElse(
                        (System.out::println),
                        () -> {
                            System.out.println("User not found");
                        }
                );
    }
}
