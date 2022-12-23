import http from "../http-common";

class UserDataService {
    getAll() {
        return http.get("/usersData");
    }

    
    get(id) {
        return http.get(`/usersData/${id}`);
    }

    create(data) {
        return http.post("/usersData", data);
    }

    update(id, data) {
        return http.put(`/usersData/${id}`, data);
    }

    delete(id) {
        return http.delete(`/usersData/${id}`);
    }

    addPromotion(id, userId) {
        return http.put(`/usersData/promotions/${userId}/${id}`);
    }

    addAction(id, userId) {
        return http.put(`/usersData/actions/${userId}/${id}`);
    }

}

export default new UserDataService();