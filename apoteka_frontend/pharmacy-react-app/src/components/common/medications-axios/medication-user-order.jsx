import http from "../http-common";

class MedicationUserOrderService {
  getAll() {
    return http.get("/medicationUserOrderData");
  }

  get(id) {
    return http.get(`/medicationUserOrderData/${id}`);
  }

  create(data) {
    return http.post("/medicationUserOrderData", data);
  }

  getOrderByUser(id) {
    return http.get(`/medicationUserOrderData/user/${id}`);
  }


  update(id, data) {
    return http.put(`/medicationUserOrderData/${id}`, data);
  }

  delete(id) {
    return http.delete(`/medicationUserOrderData/${id}`);
  }

}

export default new MedicationUserOrderService();