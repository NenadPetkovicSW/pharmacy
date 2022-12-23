import http from "../http-common";

class DermatologistsDataService {
  getAll() {
    return http.get("/dermatologistsScoreData");
  }

  getByUserAndDerm(idUser, idDerm)
  {
    return http.get(`/dermatologistsScoreData/${idUser}/${idDerm}`);
  }
  
  get(id) {
    return http.get(`/dermatologistsScoreData/${id}`);
  }

  create(data) {
    return http.post("/dermatologistsScoreData", data);
  }

  update(id, data) {
    return http.put(`/dermatologistsScoreData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/dermatologistsScoreData/${id}`);
  }
}

export default new DermatologistsDataService();