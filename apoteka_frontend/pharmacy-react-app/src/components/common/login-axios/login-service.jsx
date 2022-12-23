import http from "../http-common";

class LoginDataService {

  login(username, pass){
    return http.get(`/login/${username}/${pass}`);
  }
}

export default new LoginDataService();