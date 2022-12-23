import http from "../http-common";

class PaidLeaveDataService {
    getAll() {
        return http.get("/paidLeaveData");
    }

    getAll(id) {
        return http.get(`/paidLeaveData/pharmacy/${id}`);
    }
    
    get(id) {
        return http.get(`/paidLeaveData/${id}`);
    }

    create(data) {
        return http.post("/paidLeaveData", data);
    }

    update(id, data) {
        return http.put(`/paidLeaveData/${id}`, data);
    }
}

export default new PaidLeaveDataService();