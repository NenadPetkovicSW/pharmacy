import http from "../http-common";

class ActionsDataService {
  getAll() {
    return http.get("/actionsData");
  }

  getAll(id) {
    return http.get(`/actionsData/pharmacy/${id}`);
  }

  
  get(id) {
    return http.get(`/actionsData/${id}`);
  }

  create(data) {
    return http.post("/actionsData", data);
  }

  update(id, data) {
    return http.put(`/actionsData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/actionsData/${id}`);
  }
}

export default new ActionsDataService();