import http from "../http-common";

class PharmaciesDataService {
  getAll() {
    return http.get("/pharmaciesData");
  }

  getFree() {
    return http.get("/pharmaciesData/free");
  }

  getFreePharmaciestByDateAndTime(date, from, to)
  {
        return http.get(`/pharmaciesData/free/${date}/${from}/${to}`);
  }

  getScorable(idUser)
  {
    return http.get(`/pharmaciesData/score/${idUser}`);
  }

  getAllPromotions(id) {
    return http.get(`/pharmaciesData/promotions/${id}`);
  }

  getAllActions(id) {
    return http.get(`/pharmaciesData/actions/${id}`);
  }

  
  get(id) {
    return http.get(`/pharmaciesData/${id}`);
  }

  create(data) {
    return http.post("/pharmaciesData", data);
  }

  update(id, data) {
    return http.put(`/pharmaciesData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/pharmaciesData/${id}`);
  }
}

export default new PharmaciesDataService();