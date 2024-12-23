소개

CJU 식당 메뉴 조회 앱은 사용자가 요일별로 식당 메뉴를 조회하고 추가할 수 있는 Java 기반의 데스크톱 응용 프로그램입니다. 이 앱은 GUI를 통해 간단하고 직관적인 사용자 경험을 제공합니다.

주요 기능

메뉴 조회:

원하는 요일을 입력하면 해당 요일의 메뉴를 확인할 수 있습니다.

메뉴 데이터는 프로그램 시작 시 파일에서 로드됩니다.

메뉴 추가:

새로운 메뉴를 요일별로 추가할 수 있습니다.

추가된 메뉴는 종료 시 파일에 저장됩니다.

종료:

현재까지 변경된 데이터를 menu.txt 파일에 저장하고 프로그램을 종료합니다.

실행 방법

환경 설정:

Java Development Kit (JDK) 8 이상이 설치되어 있어야 합니다.

소스 코드 컴파일:

javac CJURestaurantApp.java

프로그램 실행:

java CJURestaurantApp

menu.txt 파일:

프로그램 실행 디렉터리에 menu.txt 파일이 있어야 합니다.

파일이 없으면, 프로그램이 새로운 데이터를 저장합니다.

파일 설명

menu.txt:

요일별 메뉴 데이터를 저장하는 텍스트 파일.

형식: 요일,메뉴1,메뉴2,...

예시:

월,김밥,라면
화,볶음밥,돈까스

프로그램 구조

주요 클래스

CJURestaurantApp: 프로그램의 메인 클래스.

주요 메서드

main: 프로그램의 진입점으로, 메뉴 데이터를 로드하고 GUI를 실행합니다.

createAndShowGUI: GUI 구성과 버튼 이벤트를 처리합니다.

viewMenuGUI: 특정 요일의 메뉴를 조회하는 GUI 기능을 제공합니다.

addMenuGUI: 사용자가 입력한 메뉴를 특정 요일에 추가하는 GUI 기능을 제공합니다.

loadMenuFromFile: 파일에서 메뉴 데이터를 읽어와 컬렉션에 저장합니다.

saveMenuToFile: 컬렉션 데이터를 파일에 저장합니다.

요구사항

파일 입출력:

menu.txt 파일을 읽고 쓰는 기능을 구현.

컬렉션 프레임워크:

Map과 List를 사용하여 데이터를 관리.

사용 기술

프로그래밍 언어: Java

GUI: Swing 라이브러리

컬렉션 프레임워크: HashMap, ArrayList

문제 해결 및 확장

메뉴 초기화 문제:

파일이 없을 경우 프로그램이 정상적으로 동작하며, 새로운 데이터를 생성하여 저장합니다.

확장 가능성:

요일 이외에 날짜별 메뉴 추가.

메뉴 검색 및 삭제 기능 추가.

파일 형식을 JSON 또는 데이터베이스로 확장.

주의사항

menu.txt 파일의 형식을 유지하세요. 형식이 맞지 않으면 데이터 로드에 실패할 수 있습니다.

프로그램 종료 전에 데이터를 반드시 저장하려면 "종료" 버튼을 사용하세요.

