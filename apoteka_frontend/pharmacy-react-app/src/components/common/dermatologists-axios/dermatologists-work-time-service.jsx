import http from "../http-common";

class DermatologistsWorkTimeDataService {
  getAll() {
    return http.get("/dermatologistsWorkTimeData");
  }

  getAll(id){
    return http.get(`/dermatologistsWorkTimeData/all/${id}`);
  }

  getAllPharmacy(id,pharmacy){
    return http.get(`/dermatologistsWorkTimeData/pharmacyAll/${id}/${pharmacy}`);
  }

  
  get(id) {
    return http.get(`/dermatologistsWorkTimeData/${id}`);
  }

  create(data) {
    return http.post("/dermatologistsWorkTimeData", data);
  }

  update(id, data) {
    return http.put(`/dermatologistsWorkTimeData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/dermatologistsWorkTimeData/${id}`);
  }
}

export default new DermatologistsWorkTimeDataService();