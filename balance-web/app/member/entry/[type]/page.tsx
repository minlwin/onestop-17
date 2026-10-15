import MemberTemplate from "@/components/widgets/pages/member-template";

export default async function LedgerEntryPage(props : PageProps<'/member/entry/[type]'>) {

    const { type } = await props.params 

    return (
        <MemberTemplate page={`${type == 'credit' ? 'Credit' : "Debit"} Management`}>
            <></>
        </MemberTemplate>
    )
}