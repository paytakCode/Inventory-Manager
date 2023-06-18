# Inventory-Manager Back-End API

로그인 정보가 DB 정보와 일치하면 JWT을 발급합니다.

매 요청마다 JwtFilter를 통해 JWT이 유효한지 검증 후 유저에 맞게 인가합니다.

모든 요청의 응답은 ResponseEntity로 반환됩니다.

기본적으로 RESTful API의 기준으로 작성하였습니다.

각 요청이 성공 시 반환하는 코드는 다음과 같습니다
  GET - 200
  POST - 201
  UPDATE - 204
  DELETE - 204
