import http from "../http-common";

class AllergiesDataService {
    getAll() {
        return http.get("/allergiesData");
    }

    
    get(id) {
        return http.get(`/allergiesData/${id}`);
    }

    getMedicationAllergies(id) {
        return http.get(`/allergiesData/medication/${id}`);
    }

    create(data) {
        return http.post("/allergiesData", data);
    }

    update(id, data) {
        return http.put(`/allergiesData/${id}`, data);
    }

    delete(id) {
        return http.delete(`/allergiesData/${id}`);
    }
}

export default new AllergiesDataService();