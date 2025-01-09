package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			//CREATE PERMISSIONS
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			//CREATE ROLES
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionsList(Set.of(createPermission,readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionsList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionsList(Set.of(createPermission,readPermission,updatePermission,deletePermission,refactorPermission))
					.build();

			//CREATE USERS

			UserEntity userMatias = UserEntity.builder()
					.username("Matias")
					.password("$2a$10$8SOkjnt7.UQAMGNujlzyHOV4scSbbgSeWZn/Ak2pYy/p5LmVXZH2y")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("Daniel")
					.password("$2a$10$8SOkjnt7.UQAMGNujlzyHOV4scSbbgSeWZn/Ak2pYy/p5LmVXZH2y")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("Andrea")
					.password("$2a$10$8SOkjnt7.UQAMGNujlzyHOV4scSbbgSeWZn/Ak2pYy/p5LmVXZH2y")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userJuli = UserEntity.builder()
					.username("Juli")
					.password("$2a$10$8SOkjnt7.UQAMGNujlzyHOV4scSbbgSeWZn/Ak2pYy/p5LmVXZH2y")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userMatias, userJuli, userAndrea, userDaniel));
		};
	}
}
