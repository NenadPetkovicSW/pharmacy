import http from "../http-common";

class MedicationsDataService {
  getAll() {
    return http.get("/medicationsData");
  }

  
  get(id) {
    return http.get(`/medicationsData/${id}`);
  }

  getScorable(idUser)
  {
    return http.get(`/medicationsData/score/${idUser}`);
  }

  create(data) {
    return http.post("/medicationsData", data);
  }

  update(id, data) {
    return http.put(`/medicationsData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/medicationsData/${id}`);
  }

  getByPharmacyId(id){
    var medications = http.get(`/medicationsData/pharmacy/${id}`);
    return medications;
  }
}

export default new MedicationsDataService();