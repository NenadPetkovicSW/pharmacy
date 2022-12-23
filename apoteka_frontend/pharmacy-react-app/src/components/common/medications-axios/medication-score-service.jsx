import http from "../http-common";

class DermatologistsDataService {
  getAll() {
    return http.get("/medicationScoreData");
  }

  getByUserAndDerm(idUser, idMed)
  {
    return http.get(`/medicationScoreData/${idUser}/${idMed}`);
  }
  
  get(id) {
    return http.get(`/medicationScoreData/${id}`);
  }

  create(data) {
    return http.post("/medicationScoreData", data);
  }

  update(id, data) {
    return http.put(`/medicationScoreData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/medicationScoreData/${id}`);
  }
}

export default new DermatologistsDataService();