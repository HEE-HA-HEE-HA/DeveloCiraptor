package org.example.develociraptor.domain.techstack.dummy;

import java.util.Arrays;
import java.util.List;
import org.example.develociraptor.domain.techstack.entity.TechStack;

public class TechStackDummy {

	private static final String[] techStackNames =
		{
			"JavaScript", "TypeScript", "React", "Vue", "Nodejs", "Spring", "Java", "Nextjs",
			"Nestjs", "Express", "Go", "C", "Python", "Django", "Swift", "Kotlin", "MySQL",
			"MongoDB", "php", "GraphQL", "Firebase", "ReactNative", "Unity", "Flutter",
			"Kubernetes", "Docker", "Figma", "Git", "AWS"
		};


	public static List<TechStack> createTechStackDummys() {
		return Arrays.stream(techStackNames).map(TechStack::createTechStack).toList();
	}

}
