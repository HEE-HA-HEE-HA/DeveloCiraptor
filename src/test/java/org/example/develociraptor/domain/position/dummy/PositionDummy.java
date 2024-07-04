package org.example.develociraptor.domain.position.dummy;

import java.util.Arrays;
import java.util.List;
import org.example.develociraptor.domain.position.entity.Position;

public class PositionDummy {

	private String[] positionNames =
		{
			"프론트",
			"백엔드",
			"디자이너",
			"IOS",
			"안드로이드",
			"데브옵스",
			"PM",
			"기획자",
			"마케터"
		};


	public List<Position> getPositionDummys() {
		return Arrays.stream(positionNames).map(Position::new).toList();
	}

}
