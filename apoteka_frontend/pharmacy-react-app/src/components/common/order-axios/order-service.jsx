import http from "../http-common";

class OrdersService {
    getAll() {
        return http.get("/medicationOrderData");
    }

    
    get(id) {
        return http.get(`/medicationOrderData/${id}`);
    }

    create(data) {
        return http.post("/medicationOrderData", data);
    }
}

export default new OrdersService();