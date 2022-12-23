import http from "../http-common";

class PromotionsDataService {
  getAll() {
    return http.get("/promotionsData");
  }

  getAll(id) {
    return http.get(`/promotionsData/pharmacy/${id}`);
  }

  get(id) {
    return http.get(`/promotionsData/${id}`);
  }

  create(data) {
    return http.post("/promotionsData", data);
  }

  update(id, data) {
    return http.put(`/promotionsData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/promotionsData/${id}`);
  }
}

export default new PromotionsDataService();