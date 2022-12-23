import http from "../http-common";

class DermatologistsDataService {
  getAll() {
    return http.get("/pharmacistScoreData");
  }

  getByUserAndDerm(idUser, idDerm)
  {
    return http.get(`/pharmacistScoreData/${idUser}/${idDerm}`);
  }
  
  get(id) {
    return http.get(`/pharmacistScoreData/${id}`);
  }

  create(data) {
    return http.post("/pharmacistScoreData", data);
  }

  update(id, data) {
    return http.put(`/pharmacistScoreData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/pharmacistScoreData/${id}`);
  }
}

export default new DermatologistsDataService();