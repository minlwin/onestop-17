import z from "zod"
import { PageResult, PageSearch, SelectOption } from ".."

export type EntryType = "Debit" | "Credit"

export const entryTypeOptions : SelectOption[] = [
    {label : "Debit", value : "Debit"},
    {label : "Credit", value : "Credit"},
]

export interface BalanceSearch {
    from?: string
    to?: string
}

export type BalancePageSearch = BalanceSearch & PageSearch

export interface BalanceListItem {
    id: string
    ledgerName: string
    particular: string
    debit: number
    credit: number
}

export interface BalanceSearchResult {
    openingBalance : number
    closingBalance : number
    result : PageResult<BalanceListItem>
}

export interface LedgerSearch {
    type?: string
}

export interface LedgerListItem {
    id: string
    type: string
    name: string
    description: string
    disabled: boolean
    total : number
}

export const ledgerSchema = z.object({
    type: z.string().nonempty("Please select entry type"),
    name : z.string().nonempty("Please select entry name"),
    description : z.string().nonempty("Please enter description of ledger")
})

export type LedgerForm = z.infer<typeof ledgerSchema>

export interface EntrySearch {
    from?: string
    to?: string
    keyword?: string
}

export type EntryPageSearch = EntrySearch & PageSearch

export interface EntryListItem {
    id: string
    entryDate: string
    enterBy: string
    type: string
    ledgerName: string
    particular: string
    amount: number
}

export const entrySchema = z.object({
    ledgerId : z.string().nonempty("Please select ledger"),
    issueDate: z.string().nonempty("Please enter issue date"),
    particular: z.string().nonempty("Please enter particular information"),
    items: z.array(z.object({
        article : z.string().nonempty("Please enter article"),
        quantity : z.string().nonempty("Please enter quantity"),
        unitPrice : z.string().nonempty("Please enter unit price"),
    })).nonempty("Please enter entry item")
})

export type EntryForm = z.infer<typeof entrySchema>

export interface EntryDetails {
    id: string
    type: string
    ledgerName: string
    particular: string
    issueAt: string
    enterBy: string
    items : {
        article : string
        quantity : number
        unitPrice : number
        amount : number
    } []
}