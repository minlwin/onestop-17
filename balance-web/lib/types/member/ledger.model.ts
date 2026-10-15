export type LedgerType = "debit" | "credit"

export const ledgerTypes = {
    "debit" : "Debit",
    "credit" : "Credit"
} satisfies Record<LedgerType, string>

export interface LedgerSearch {
    type? :LedgerType
    deleted? : string
    keyword? : string
}

export interface LedgerListItem {
    id: string
    type: string
    name : string
    amount : number
    active : boolean
    createdAt : string
    modifiedAt : string
}

export type LedgerDetails = LedgerListItem & {
    createdBy : string
    modifiedBy : string
}