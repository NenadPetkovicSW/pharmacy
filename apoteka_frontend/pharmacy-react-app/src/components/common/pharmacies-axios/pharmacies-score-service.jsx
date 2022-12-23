import http from "../http-common";

class DermatologistsDataService {
  getAll() {
    return http.get("/pharmacyScoreData");
  }

  getByUserAndDerm(idUser, idMed)
  {
    return http.get(`/pharmacyScoreData/${idUser}/${idMed}`);
  }
  
  get(id) {
    return http.get(`/pharmacyScoreData/${id}`);
  }

  create(data) {
    return http.post("/pharmacyScoreData", data);
  }

  update(id, data) {
    return http.put(`/pharmacyScoreData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/pharmacyScoreData/${id}`);
  }
}

export default new DermatologistsDataService();