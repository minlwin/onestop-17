import { ModificationResult, PaymentInfo, PaymentInfoForm } from "@/lib/types";

export async function findAll() : Promise<PaymentInfo[]> {
    return [ dummyInfo ]
}

export async function findById(id : any) : Promise<PaymentInfo> {
    return dummyInfo
}

export async function create(form : PaymentInfoForm) : Promise<ModificationResult<number>> {
    return {
        result : 1
    }
}

export async function update(id : any, form : PaymentInfoForm) : Promise<ModificationResult<number>> {
    return {
        result : id
    }
}

const dummyInfo: PaymentInfo = {
    id: 1,
    provider: "KBZ Pay",
    accountNo: "09782003098",
    accountName: "U Zaw Min Lwin",
    createdAt : "2026-10-01 10:00",
    createdBy : "John Doe",
    modifiedAt : "",
    modifiedBy: ""
}