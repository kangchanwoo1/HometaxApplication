package connector.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HometaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(HometaxApplication.class, args);
    }

    // 스프링 부트가 시작될 때 자동으로 실행되는 테스트 코드
    @Bean
    public CommandLineRunner runTest(BoardDAO boardDAO) {
        return args -> {
            System.out.println("--- 1. 게시글 등록 테스트 시작 ---");
            
            // 테스트용 DTO 생성
            BoardDTO dto = new BoardDTO();
            dto.setTitle("스프링 부트 첫 게시글!");
            dto.setContent("JdbcTemplate으로 성공적으로 등록되었습니다.");
            dto.setWriter("홍길동");
            
            // DAO 메서드 호출 (스프링이 관리하는 boardDAO이므로 jdbcTemplate이 null이 아닙니다!)
            boardDAO.insertBoard(dto);
            
            System.out.println("--- 게시글 등록 테스트 종료 ---");
        };
    }
}