package com.gg.administrative_system_backend.contract;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Contract {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private boolean status;
        private float totalExpenses;
        public Contract(){}
        public Contract(String name){
            this.name = name;
            this.status = true;
            this.totalExpenses = 0;
        }

        public long getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public float getTotalExpenses() {
            return totalExpenses;
        }

        public void setTotalExpenses(float totalExpenses) {
            this.totalExpenses = totalExpenses;
        }
}
