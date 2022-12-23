import http from "../http-common";

class DermatologistsDataService {
  getAll() {
    return http.get("/dermatologistsData");
  }

  getAllSearch(name,lastname, pharmacyid){
    return http.get(`/dermatologistsData/search/${name}/${lastname}/${pharmacyid}`);
  }

  getScorable(idUser)
  {
    return http.get(`/dermatologistsData/score/${idUser}`);
  }
  
  get(id) {
    return http.get(`/dermatologistsData/${id}`);
  }

  create(data) {
    return http.post("/dermatologistsData", data);
  }

  findShift(dId, pId) {
    return http.get(`/dermatologistsData/${dId}/${pId}`);
  }

  update(id, data) {
    return http.put(`/dermatologistsData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/dermatologistsData/${id}`);
  }
}

export default new DermatologistsDataService();