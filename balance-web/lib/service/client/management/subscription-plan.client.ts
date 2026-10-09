import { ModificationResult, SubscriptionPlan, SubscriptionPlanForm } from "@/lib/types";

export async function findAll() : Promise<SubscriptionPlan[]> {
    return [
        dummyPlan
    ]
}

export async function findById(id : any) : Promise<SubscriptionPlan> {
    return dummyPlan
}

export async function create(form : SubscriptionPlanForm) : Promise<ModificationResult<number>> {
    return {
        result : 1
    }
}

export async function update(id : any, form : SubscriptionPlanForm) : Promise<ModificationResult<number>> {
    return {
        result : id
    }
}

const dummyPlan:SubscriptionPlan = {
    id : 1,
    name : "Free",
    description : "Free plan to use balance management system.",
    maxEmployee : "3",
    maxLedger : "10",
    maxEntry : "10",
    price : "0",
    features : [
        {name : "Feature 1"},
        {name : "Feature 2"},
    ],
    createdAt : "2026-10-01 10:00",
    createdBy : "John Doe",
    modifiedAt : "",
    modifiedBy: ""
}