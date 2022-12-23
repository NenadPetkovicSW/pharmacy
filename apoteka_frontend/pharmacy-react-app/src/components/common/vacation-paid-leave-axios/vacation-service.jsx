import http from "../http-common";

class VacationDataService {
    getAll() {
        return http.get("/vacationData");
    }
    
    getAll(id) {
        return http.get(`/vacationData/pharmacy/${id}`);
    }

    get(id) {
        return http.get(`/vacationData/${id}`);
    }

    create(data) {
        return http.post("/vacationData", data);
    }

    update(id, data, approved, message) {
        return http.put(`/vacationData/${id}/${approved}/${message}`, data);
    }
}

export default new VacationDataService();